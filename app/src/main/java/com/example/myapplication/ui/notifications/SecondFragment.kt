package com.example.myapplication.ui.second

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.SecondRandomgiftBinding
import com.example.myapplication.ui.notifications.NotificationsViewModel

class SecondFragment : Fragment() {

    private var _binding: SecondRandomgiftBinding? = null

    private val binding get() = _binding!!
    private lateinit var friendList: List<String>

//    adapter 만들어야함

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SecondRandomgiftBinding.inflate(inflater, container, false)
        val root: View = binding.root
        friendList = listOf("friend1", "friend2", "friend3")

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.friendButton.setOnClickListener {
            showFriendSearchDialog(requireContext(),friendList)
            findNavController().navigate(R.id.action_second_to_result)
        }
    }

    private fun showFriendSearchDialog(context: Context, friendList: List<String>) {
        // Inflate the custom layout
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_friend_gift, null)

        val searchInput = dialogView.findViewById<EditText>(R.id.friendnameInput)
        val listView = dialogView.findViewById<ListView>(R.id.friendlistView)
        val priceRangeText = dialogView.findViewById<TextView>(R.id.friendpriceInput)
        val priceSeekBar = dialogView.findViewById<SeekBar>(R.id.price_seekbar)
        val friendaddButton = dialogView.findViewById<Button>(R.id.friendaddButton)

//        친구 리스트 표시를 위한 어댑터
        val mutableFriendList = friendList.toMutableList()
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, friendList)
        listView.adapter = adapter

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter the list based on the search query
                val filteredList = friendList.filter { it.contains(s.toString(), ignoreCase = true) }
                mutableFriendList.clear()
                mutableFriendList.addAll(filteredList)
                adapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        // Set initial values for price range
        priceSeekBar.max = 100000 // Example max price
        priceSeekBar.progress = 5000 // Default progress
        priceRangeText.text = "Current Price: ${priceSeekBar.progress}"

        priceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                priceRangeText.text = "Current Price: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Create and show the dialog
        val dialog = AlertDialog.Builder(context)
            .setTitle("Friend Search")
            .setView(dialogView)
            .setNegativeButton("Cancel", null)
            .create()
        friendaddButton.setOnClickListener {
            val selectedFriend = searchInput.text.toString()
            val selectedPrice = priceSeekBar.progress

            // Handle the search logic here
            Toast.makeText(context, "Searching for: $selectedFriend with max price $selectedPrice", Toast.LENGTH_SHORT).show()

            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("friendName", selectedFriend)
            intent.putExtra("selectedPrice", selectedPrice)
            context.startActivity(intent)
            dialog.dismiss()
        }
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
