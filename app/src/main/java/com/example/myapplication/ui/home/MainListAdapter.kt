package com.example.myapplication.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope

class MainListAdapter (val context: Context, val productList: ArrayList<ProductInfo>) : BaseAdapter() {


    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(p0: Int): Any {
        return productList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.main_lv_item, parent, false)

        val nameTextView = view.findViewById<TextView>(R.id.NameTv)
        val priceTextView = view.findViewById<TextView>(R.id.PriceTv)
        val linkTextView = view.findViewById<TextView>(R.id.LinkTv)
        val productImageView = view.findViewById<ImageView>(R.id.productImg)

        val product = productList[position]
        nameTextView.text = product.name
        priceTextView.text = product.price
        linkTextView.text = product.link


        linkTextView.setOnClickListener {
            val url = product.link
            if(url.isNotBlank() && (url.startsWith("http://") || url.startsWith("https://"))) {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(url)
                }
                context.startActivity(intent)
            }
            else{
                Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show()
            }
        }

        if (product.imageUri != null) {
            productImageView.setImageURI(product.imageUri)
        } else {
            productImageView.setImageResource(R.drawable.gift) // 기본 이미지 설정
        }


        return view
    }
}