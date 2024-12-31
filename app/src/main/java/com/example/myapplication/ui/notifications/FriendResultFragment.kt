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
import android.widget.ImageView
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.SecondRandomgiftBinding
import com.example.myapplication.ui.notifications.NotificationsViewModel

class ThirdFragment : Fragment() {

    private var _binding: SecondRandomgiftBinding? = null

    private val binding get() = _binding!!

    private val giftList = listOf(
        "Gift 1 Info",
        "Gift 2 Info",
        "Gift 3 Info",
        "Gift 4 Info",
        "Gift 5 Info",
        "Gift 6 Info",
        "Gift 7 Info",
        "Gift 8 Info",
        "Gift 9 Info"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = SecondRandomgiftBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = GiftAdapter(giftList)

        return root
    }

    class GiftAdapter(private val giftList: List<String>) : RecyclerView.Adapter<GiftAdapter.GiftViewHolder>() {

        private val clickedItems = mutableSetOf<Int>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gift, parent, false)
            return GiftViewHolder(view)
        }

        override fun onBindViewHolder(holder: GiftViewHolder, position: Int) {
            val isClicked = clickedItems.contains(position)
            holder.giftImage.setImageResource(if (isClicked) R.drawable.openedgift else R.drawable.closedgift)

            holder.itemView.setOnClickListener {
                clickedItems.add(position)
                notifyItemChanged(position)

                AlertDialog.Builder(holder.itemView.context)
                    .setTitle("Gift Information")
                    .setMessage(giftList[position])
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        override fun getItemCount(): Int = giftList.size

        class GiftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val giftImage: ImageView = itemView.findViewById(R.id.giftImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
