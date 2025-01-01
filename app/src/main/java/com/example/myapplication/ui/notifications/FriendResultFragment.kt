package com.example.myapplication.ui.third

import android.content.Context
import android.content.Intent
import android.net.Uri

import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.SecondRandomgiftBinding
import com.example.myapplication.data.GiftItem2
import com.example.myapplication.data.giftInfoList2
import com.example.myapplication.ui.notifications.NotificationsViewModel

class ThirdFragment : Fragment() {

    private var _binding: SecondRandomgiftBinding? = null

    private val binding get() = _binding!!
//    adapter 만들어야함



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = SecondRandomgiftBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = GiftAdapter(giftInfoList2) { giftItem ->
            showCustomDialog(giftItem)
        }

        return root
    }

    private fun showCustomDialog(giftItem: GiftItem2) {
        val customdialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_gift_info, null)

        val giftNameTextView = customdialogView.findViewById<TextView>(R.id.giftname)
        val giftImageView = customdialogView.findViewById<ImageView>(R.id.selectedgiftImg)
        val giftPriceTextView = customdialogView.findViewById<TextView>(R.id.giftprice)
        val giftLinkTextView = customdialogView.findViewById<TextView>(R.id.giftlink)

        // Populate data
        giftNameTextView.text = giftItem.name
        giftImageView.setImageResource(giftItem.imageRes.takeIf { it != 0 } ?: R.drawable.openedgift)
        giftPriceTextView.text = giftItem.price
        giftLinkTextView.text = giftItem.link

        // Set click listener for the link
        giftLinkTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(giftItem.link))
            startActivity(intent)
        }

        AlertDialog.Builder(requireContext())
            .setView(customdialogView)
            .setCancelable(true)
            .setPositiveButton("닫기") { dialog, _ ->
                dialog.dismiss()
                findNavController().navigate(R.id.action_end_to_start)
            }
            .create()
            .show()
    }

    class GiftAdapter(
        private val items: List<GiftItem2>,
        private val onItemClick: (GiftItem2) -> Unit = {}
    ) : RecyclerView.Adapter<GiftAdapter.GiftViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_gift, parent, false)
            return GiftViewHolder(view)
        }

        override fun onBindViewHolder(holder: GiftViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        inner class GiftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val giftImageView: ImageView = itemView.findViewById(R.id.giftImage)

            fun bind(giftItem: GiftItem2) {
                giftImageView.setOnClickListener {
                    onItemClick(giftItem)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

