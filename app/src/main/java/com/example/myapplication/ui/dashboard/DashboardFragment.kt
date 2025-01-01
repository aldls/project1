package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment


import android.app.Activity
import android.content.Intent
import android.net.Uri

import android.provider.MediaStore

import android.widget.Button
import android.widget.EditText

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.FileProvider
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale





class GalleryFragment : Fragment() {






    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonAddPhoto: Button
    //private val imageList = mutableListOf<String>()
    private lateinit var photoFile: File
    private lateinit var adapter: GalleryAdapter

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_SELECT_PHOTO = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        buttonAddPhoto = view.findViewById(R.id.buttonAddPhoto)
        dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        setupRecyclerView()
        buttonAddPhoto.setOnClickListener { showOptionsDialog()  }

        //loadImagesFromStorage()
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavView.post {
            val bottomNavHeight = bottomNavView.height

            // Adjust fragment content padding based on BottomNavigationView height
            view.setPadding(0, 0, 0, bottomNavHeight)
        }
    }

    private fun setupRecyclerView() {
        adapter = GalleryAdapter(dashboardViewModel.imageList)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
    }

    private fun showOptionsDialog() {
        val options = arrayOf("Take a Photo", "Select from Gallery")
        AlertDialog.Builder(requireContext())
            .setTitle("Choose an Option")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> openCamera() // Option 1: Take a Photo
                    1 -> openGallery() // Option 2: Select from Gallery
                }
            }
            .show()
    }


    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val storageDir = requireContext().getExternalFilesDir("Photos")
        photoFile = File(storageDir, generateFileName())

        val photoURI = FileProvider.getUriForFile(
            requireContext(),
            "com.example.myapplication.fileprovider",
            photoFile
        )

        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_SELECT_PHOTO)
    }

    private fun generateFileName(): String {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return "IMG_$timestamp.jpg"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    showTextInputDialog(photoFile.absolutePath)
                }
                REQUEST_SELECT_PHOTO -> {
                    val selectedImageUri = data?.data
                    val filePath = selectedImageUri?.let { getRealPathFromURI(it) }
                    filePath?.let { showTextInputDialog(it) }
                }
            }
        }
    }
    private fun showTextInputDialog(imagePath: String) {
        val inputEditText = EditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("Enter Text for Image")
            .setView(inputEditText)
            .setPositiveButton("OK") { _, _ ->
                val userInput = inputEditText.text.toString()
                dashboardViewModel.imageList.add(Pair(imagePath, userInput))
                adapter.notifyItemInserted(dashboardViewModel.imageList.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    private fun getRealPathFromURI(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return it.getString(columnIndex)
            }
        }
        return null
    }
}