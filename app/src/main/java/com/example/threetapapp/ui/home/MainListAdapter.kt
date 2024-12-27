package com.example.threetapapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.threetapapp.R

class MainListAdapter (val context: Context, val productinfoList: ArrayList<ProductInfo>) : BaseAdapter() {


    override fun getCount(): Int {
        return productinfoList.size
    }

    override fun getItem(p0: Int): Any {
        return productinfoList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.main_lv_item, null)

        val profileImg = view.findViewById<ImageView>(R.id.profileImg)
        val name = view.findViewById<TextView>(R.id.NameTv)
        val price = view.findViewById<TextView>(R.id.PriceTv)
        val link = view.findViewById<TextView>(R.id.LinkTv)

        val product = productinfoList[position]
        val resourceId = context.resources.getIdentifier(product.photo, "drawable", context.packageName)
        profileImg.setImageResource(resourceId)
        name.text = product.name
        price.text = product.price
        link.text = product.link

        return view
    }
}