package com.example.myapplication.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.example.myapplication.R

class GalleryAdapter(private val imageList: List<Pair<String, String>>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView=itemView.findViewById((R.id.textViewTitle))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val (imagePath, text) = imageList[position]
        Glide.with(holder.itemView.context)
            .load(imagePath)
            .into(holder.imageView)
        holder.textView.text = text

        holder.itemView.setOnClickListener {
            val dialog = AlertDialog.Builder(holder.itemView.context).create()
            val dialogView = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.dialog_enlarged_view, null)

            val imageView = dialogView.findViewById<ImageView>(R.id.enlargedImageView)
            val textView = dialogView.findViewById<TextView>(R.id.enlargedTextView)

            Glide.with(holder.itemView.context)
                .load((imageList[position].first))
                .into(imageView)
            textView.text = text
            dialog.setView(dialogView)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // Make background transparent
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation // Apply animation
            dialog.show()
        }

    }


    override fun getItemCount(): Int = imageList.size
}
