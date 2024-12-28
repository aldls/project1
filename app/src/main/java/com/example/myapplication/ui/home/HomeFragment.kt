package com.example.myapplication.ui.home


import android.app.Dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    
    private lateinit var productinfoList: ArrayList<ProductInfo>
    private lateinit var adapter: MainListAdapter
    


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =

            ViewModelProvider(this).get(HomeViewModel::class.java)

            ViewModelProvider(this).get(HomeViewModel::class.java) //ViewModel 초기화


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        productinfoList = arrayListOf(
            ProductInfo("red shoes","11,000","www.naver.com","humanicon"),
            ProductInfo("blue shoes","12,000","www.naver.com","humanicon"),
            ProductInfo("red pants","43,000","www.naver.com","humanicon"),
            ProductInfo("jacket","11,000","www.naver.com","humanicon"),
            ProductInfo("pink shorts","16,000","www.naver.com","humanicon")
        )
        
        adapter = MainListAdapter(requireContext(), productinfoList)
        
        val listView: ListView = root.findViewById(R.id.mainListView)
        listView.adapter = adapter

//        팝업으로 입력 받기
        val addButton: Button = root.findViewById(R.id.addButton)
        addButton.setOnClickListener{
            showAddProductDialog()
        }
        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAddProductDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_product)
        dialog.setCancelable(true)

        val nameInput: EditText = dialog.findViewById(R.id.dialognameInput)
        val priceInput: EditText = dialog.findViewById(R.id.dialogpriceInput)
        val linkInput: EditText = dialog.findViewById(R.id.dialoglinkInput)
        val addButton: Button = dialog.findViewById(R.id.dialogaddButton)

        addButton.setOnClickListener{
            val name = nameInput.text.toString().trim()
            val price = priceInput.text.toString().trim()
            val link = linkInput.text.toString().trim()

            if(name.isEmpty()){
                Toast.makeText(requireContext(), "Please enter product name", Toast.LENGTH_SHORT).show()
            }
            else{
                val newProduct = ProductInfo(name, price, link, "humanicon")
                productinfoList.add(newProduct)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
                Toast.makeText(requireContext(), "Product added", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

}