package com.example.threetapapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.threetapapp.R
import com.example.threetapapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    
    private lateinit var contactinfoList: ArrayList<Contactinfo>
    private lateinit var adapter: MainListAdapter
    

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java) //ViewModel 초기화

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        contactinfoList = arrayListOf(
            Contactinfo("Jeong Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Alice","010-7890-7890","william.alice.harrison@example-pet-store.com","humanicon"),
            Contactinfo("Jeong Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Alice","010-7890-7890","william.alice.harrison@example-pet-store.com","humanicon"),
            Contactinfo("Jeong Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Jeong Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Alice","010-7890-7890","william.alice.harrison@example-pet-store.com","humanicon"),
            Contactinfo("Jeong Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Alice","010-7890-7890","william.alice.harrison@example-pet-store.com","humanicon"),
            Contactinfo("Jeong Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Jeong Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Alice","010-7890-7890","william.alice.harrison@example-pet-store.com","humanicon"),
            Contactinfo("Jeong Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Alice","010-7890-7890","william.alice.harrison@example-pet-store.com","humanicon"),
            Contactinfo("Jeong inho", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Kang Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("July","010-7890-7890","william.alice.harrison@example-pet-store.com","humanicon"),
            Contactinfo("Kim Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon"),
            Contactinfo("Mark","010-7890-7890","william.alice.harrison@example-pet-store.com","humanicon"),
            Contactinfo("Park Minji", "010-1234-1234", "william.minji.harrison@example-pet-store.com", "humanicon")
        )
        
        adapter = MainListAdapter(requireContext(), contactinfoList)
        
        val listView: ListView = root.findViewById(R.id.mainListView)
        listView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}