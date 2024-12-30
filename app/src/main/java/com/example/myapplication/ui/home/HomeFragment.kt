package com.example.myapplication.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.ui.text.intl.Locale
import java.util.Locale
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import java.io.File
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var productinfoList: ArrayList<ProductInfo>
    private lateinit var adapter: MainListAdapter

    private val galleryResult = registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri? ->
        uri?.let{
            selectedImageUri = it
        }
    }
    private val cameraResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
        if (success) {
            // 카메라로 촬영한 이미지 URI 처리
            selectedImageUri = Uri.fromFile(photoFile)
        }
    }

    private var selectedImageUri: Uri? = null
    private lateinit var photoFile: File

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java) //ViewModel 초기화

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        productinfoList = arrayListOf()

        adapter = MainListAdapter(requireContext(), productinfoList)

        val listView: ListView = root.findViewById(R.id.mainListView)
        listView.adapter = adapter

//        팝업으로 입력 받기
        val addButton: Button = root.findViewById(R.id.addButton)
        addButton.setOnClickListener {
            showAddProductDialog()
        }
        return root

    }

    private fun showAddProductDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_product)
        dialog.setCancelable(true)

        val nameInput: EditText = dialog.findViewById(R.id.dialognameInput)
        val priceInput: EditText = dialog.findViewById(R.id.dialogpriceInput)
        val linkInput: EditText = dialog.findViewById(R.id.dialoglinkInput)
        val selectPhotoButton: Button = dialog.findViewById(R.id.dialogselectPhotoButton)
        val addButton: Button = dialog.findViewById(R.id.dialogaddButton)


        selectPhotoButton.setOnClickListener {
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

        addButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val price = priceInput.text.toString().trim()
            val link = linkInput.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter product name", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val newProduct = ProductInfo(
                    name,
                    price,
                    link,
                    selectedImageUri ?: Uri.parse("android.resource://${requireContext().packageName}/${R.drawable.gift}"))
                productinfoList.add(newProduct)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
                Toast.makeText(requireContext(), "Product added", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun pickImageFromGallery() {
        // 갤러리에서 이미지를 선택
        galleryResult.launch("image/*")
    }

    private fun takePhoto() {
        val storageDir = requireContext().getExternalFilesDir("Photos")
        photoFile = File(storageDir, createImageFile())
        val photoUri = FileProvider.getUriForFile(
            requireContext(),
            "com.example.myapplication.fileprovider",
            photoFile
        )
        cameraResult.launch(photoUri)
    }


    private fun createImageFile(): String {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return "IMG_$timestamp.jpg"
    }

}

