package com.example.myapplication.ui.dashboard
<<<<<<< HEAD
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.FileProvider

import android.widget.TextView

import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class GalleryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonAddPhoto: Button
    private val imageList = mutableListOf<String>()
    private lateinit var photoFile: File

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        buttonAddPhoto = view.findViewById(R.id.buttonAddPhoto)

        setupRecyclerView()
        buttonAddPhoto.setOnClickListener { openCamera() }

        loadImagesFromStorage()
        return view
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = GalleryAdapter(imageList)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val storageDir = requireContext().getExternalFilesDir("Photos")
        photoFile = File(storageDir, generateFileName())

        val photoURI = FileProvider.getUriForFile(requireContext(), "com.example.myapplication.fileprovider", photoFile)

        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun generateFileName(): String {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return "IMG_$timestamp.jpg"
    }

    private fun loadImagesFromStorage() {
        val storageDir = requireContext().getExternalFilesDir("Photos")
        storageDir?.listFiles()?.forEach { file ->
            if (file.extension == "jpg" || file.extension == "png") {
                imageList.add(file.absolutePath)
            }
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            imageList.add(photoFile.absolutePath)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}


=======

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
>>>>>>> minji
