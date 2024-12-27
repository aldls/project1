package com.example.threetapapp.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
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

//        팝업으로 입력 받기
        val addButton: Button = root.findViewById(R.id.addButton)
        addButton.setOnClickListener{
            showAddContactDialog()
        }
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAddContactDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_contact)
        dialog.setCancelable(true)

        val nameInput: EditText = dialog.findViewById(R.id.dialognameInput)
        val phoneInput: EditText = dialog.findViewById(R.id.dialogphoneInput)
        val emailInput: EditText = dialog.findViewById(R.id.dialogemailInput)
        val addButton: Button = dialog.findViewById(R.id.dialogaddButton)

        addButton.setOnClickListener{
            val name = nameInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()
            val email = emailInput.text.toString().trim()

            if(name.isEmpty() || phone.isEmpty()){
                Toast.makeText(requireContext(), "Please enter name and phone number", Toast.LENGTH_SHORT).show()
            }
            else{
                val newContact = Contactinfo(name, phone, email, "humanicon")
                contactinfoList.add(newContact)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
                Toast.makeText(requireContext(), "Contact added", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}