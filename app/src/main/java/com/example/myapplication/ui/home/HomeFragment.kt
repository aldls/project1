package com.example.myapplication.ui.home

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var adapter: MainListAdapter

    private lateinit var photoFile: File
    private var selectedImageUri: Uri? = null

    // Gallery result launcher
    private val galleryLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedImageUri = it
                Toast.makeText(requireContext(), "Photo selected from gallery", Toast.LENGTH_SHORT).show()
            }
        }

    // Camera result launcher
    private val cameraLauncher: ActivityResultLauncher<Uri> =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
            if (success) {
                selectedImageUri = Uri.fromFile(photoFile)
                Toast.makeText(requireContext(), "Photo taken", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize adapter
        adapter = MainListAdapter(requireContext(), mutableListOf())
        val listView: ListView = binding.mainListView
        listView.adapter = adapter

        // Observe productList from SharedViewModel
        sharedViewModel.productList.observe(viewLifecycleOwner) { productList ->
            adapter.updateList(productList)
        }

        // Add button click listener
        binding.addButton.setOnClickListener {
            showAddProductDialog()
        }
    }

    private fun showAddProductDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_product)
        dialog.setCancelable(true)
        dialog.window?.setLayout(900, 1200)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val nameInput: EditText = dialog.findViewById(R.id.dialognameInput)
        val priceInput: EditText = dialog.findViewById(R.id.dialogpriceInput)
        val linkInput: EditText = dialog.findViewById(R.id.dialoglinkInput)
        val selectPhotoButton: Button = dialog.findViewById(R.id.dialogselectPhotoButton)
        val addButton: Button = dialog.findViewById(R.id.dialogaddButton)

        selectPhotoButton.setOnClickListener {
            showPhotoOptionsDialog()
        }

        addButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val price = priceInput.text.toString().trim()
            val link = linkInput.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter product name", Toast.LENGTH_SHORT).show()
            } else {
                val newProduct = ProductInfo(
                    name = name,
                    price = price,
                    link = link,
                    imageUri = selectedImageUri ?: Uri.parse("android.resource://${requireContext().packageName}/${R.drawable.boxrmv}")
                )
                sharedViewModel.addProduct(newProduct)
                dialog.dismiss()
                Toast.makeText(requireContext(), "Product added", Toast.LENGTH_SHORT).show()

                selectedImageUri = null // Reset image URI
            }
        }

        dialog.show()
    }

    private fun showPhotoOptionsDialog() {
        val options = arrayOf("Select from Gallery", "Take a Photo")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose an option")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> pickImageFromGallery()
                1 -> takePhoto()
            }
        }
        builder.show()
    }

    private fun pickImageFromGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun takePhoto() {
        val storageDir = requireContext().getExternalFilesDir("Photos")
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        photoFile = File(storageDir, "IMG_$timestamp.jpg")
        val photoUri = FileProvider.getUriForFile(
            requireContext(),
            "com.example.myapplication.fileprovider",
            photoFile
        )
        cameraLauncher.launch(photoUri)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}