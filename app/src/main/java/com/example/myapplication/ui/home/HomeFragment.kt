package com.example.myapplication.ui.home

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var adapter: MainListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adjust layout to account for BottomNavigationView
        view.post {
            val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            bottomNavView?.let {
                val bottomNavHeight = it.height
                view.setPadding(0, 0, 0, bottomNavHeight)
            } ?: run {
                Log.e("HomeFragment", "BottomNavigationView is null")
            }
        }

        // Set up ListView
        val listView: ListView = view.findViewById(R.id.mainListView)
        sharedViewModel.productList.observe(viewLifecycleOwner) { productList ->
            adapter = MainListAdapter(requireContext(), productList)
            listView.adapter = adapter
        }

        // Add Button for Adding New Products
        val addButton: Button = view.findViewById(R.id.addButton)
        addButton.setOnClickListener {
            showAddProductDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAddProductDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_product)
        dialog.setCancelable(true)

        val nameInput: EditText = dialog.findViewById(R.id.dialognameInput)
        val priceInput: EditText = dialog.findViewById(R.id.dialogpriceInput)
        val linkInput: EditText = dialog.findViewById(R.id.dialoglinkInput)
        val addButton: Button = dialog.findViewById(R.id.dialogaddButton)

        addButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val price = priceInput.text.toString().trim()
            val link = linkInput.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter product name", Toast.LENGTH_SHORT).show()
            } else {
                val newProduct = ProductInfo(name, price, link, "humanicon")
                sharedViewModel.addProduct(newProduct)
                dialog.dismiss()
                Toast.makeText(requireContext(), "Product added", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}