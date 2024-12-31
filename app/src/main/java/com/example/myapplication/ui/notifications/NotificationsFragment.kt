package com.example.myapplication.ui.randomgift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.StartRandomgiftBinding


class RandomgiftFragment : Fragment() {

    private var _binding: StartRandomgiftBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StartRandomgiftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.friendButton.setOnClickListener {
            findNavController().navigate(R.id.action_start_to_second)
            showFriendSearchFirstDialog(requireContext(), listOf("friend1", "friend2", "friend3"))
        }
        binding.notfriendButton.setOnClickListener{
            findNavController().navigate(R.id.action_start_to_third)
            showFriendSearchSecondDialog(requireContext()){relation, age, price ->
            }

        }
    }

    private fun showFriendSearchFirstDialog(context: android.content.Context, friendList: List<String>) {
        // Inflate the custom layout
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_friend_gift, null)

        val searchInput = dialogView.findViewById<android.widget.EditText>(R.id.friendnameInput)
        val listView = dialogView.findViewById<android.widget.ListView>(R.id.friendlistView)
        val priceRangeText = dialogView.findViewById<android.widget.TextView>(R.id.friendpriceInput)
        val priceSeekBar = dialogView.findViewById<android.widget.SeekBar>(R.id.price_seekbar)
        val friendaddButton = dialogView.findViewById<android.widget.Button>(R.id.friendaddButton)

        // 친구 리스트 표시를 위한 어댑터
        val mutableFriendList = friendList.toMutableList()
        val adapter = android.widget.ArrayAdapter(context, android.R.layout.simple_list_item_1, friendList)
        listView.adapter = adapter

        searchInput.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter the list based on the search query
                val filteredList = friendList.filter { it.contains(s.toString(), ignoreCase = true) }
                mutableFriendList.clear()
                mutableFriendList.addAll(filteredList)
                adapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })
        // Set initial values for price range
        priceSeekBar.max = 100000 // Example max price
        priceSeekBar.progress = 5000 // Default progress
        priceRangeText.text = "Current Price: ${priceSeekBar.progress}"

        priceSeekBar.setOnSeekBarChangeListener(object : android.widget.SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: android.widget.SeekBar?, progress: Int, fromUser: Boolean) {
                priceRangeText.text = "Current Price: $progress"
            }

            override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: android.widget.SeekBar?) {}
        })

        // Create and show the dialog
        val firstdialog = androidx.appcompat.app.AlertDialog.Builder(context)
            .setTitle("Friend Search")
            .setView(dialogView)
            .setNegativeButton("Cancel", null)
            .create()
        friendaddButton.setOnClickListener {
            val selectedFriend = searchInput.text.toString()
            val selectedPrice = priceSeekBar.progress

            // Handle the search logic here
            android.widget.Toast.makeText(context, "Searching for: $selectedFriend with max price $selectedPrice", android.widget.Toast.LENGTH_SHORT).show()
            firstdialog.dismiss()
        }
        firstdialog.setCanceledOnTouchOutside(false)
        firstdialog.show()

        firstdialog.window?.setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

    }

    private fun showFriendSearchSecondDialog(context: android.content.Context, onSave: (String, String,Int) -> Unit) {
        // Inflate the custom layout
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_nonfriend_gift, null)

        val relationGroup = dialogView.findViewById<android.widget.RadioGroup>(R.id.relationGroup)
        val ageGroup = dialogView.findViewById<android.widget.RadioGroup>(R.id.ageGroup)
        val priceRangeText = dialogView.findViewById<android.widget.TextView>(R.id.nonfriendpriceInput)
        val priceSeekBar = dialogView.findViewById<android.widget.SeekBar>(R.id.price_seekbar)
        val nonfriendaddButton = dialogView.findViewById<android.widget.Button>(R.id.nonfriendaddButton)


        // Set initial values for price range
        priceSeekBar.max = 100000 // Example max price
        priceSeekBar.progress = 5000 // Default progress
        priceRangeText.text = "Current Price: ${priceSeekBar.progress}"

        priceSeekBar.setOnSeekBarChangeListener(object : android.widget.SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: android.widget.SeekBar?, progress: Int, fromUser: Boolean) {
                priceRangeText.text = "Current Price: $progress"
            }

            override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: android.widget.SeekBar?) {}
        })

        // Create and show the dialog
        val seconddialog = androidx.appcompat.app.AlertDialog.Builder(context)
            .setTitle("Search Present")
            .setView(dialogView)
            .setNegativeButton("Cancel", null)
            .create()
        nonfriendaddButton.setOnClickListener {
            val selectedRelationId = relationGroup.checkedRadioButtonId
            val selectedRelation = if (selectedRelationId != -1) {
                dialogView.findViewById<RadioButton>(selectedRelationId).text.toString()
            } else {
                "No relation selected"
            }

            val selectedAgeId = ageGroup.checkedRadioButtonId
            val selectedAge = if (selectedAgeId != -1) {
                dialogView.findViewById<RadioButton>(selectedAgeId).text.toString()
            } else {
                "No age selected"
            }

            val selectedPrice = priceSeekBar.progress

            onSave(selectedRelation, selectedAge, selectedPrice)

            // Handle the search logic here
            seconddialog.dismiss()
        }
        seconddialog.setCanceledOnTouchOutside(false)
        seconddialog.show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}