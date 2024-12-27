package com.example.threetapapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.threetapapp.R

class MainListAdapter (val context: Context, val contactinfoList: ArrayList<Contactinfo>) : BaseAdapter() {


    override fun getCount(): Int {
        return contactinfoList.size
    }

    override fun getItem(p0: Int): Any {
        return contactinfoList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(positoin: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.main_lv_item, null)

        val profileImg = view.findViewById<ImageView>(R.id.profileImg)
        val name = view.findViewById<TextView>(R.id.NameTv)
        val phonenum = view.findViewById<TextView>(R.id.PhonenumTv)
        val email = view.findViewById<TextView>(R.id.EmailTv)

        val contact = contactinfoList[positoin]
        val resourceId = context.resources.getIdentifier(contact.photo, "drawable", context.packageName)
        profileImg.setImageResource(resourceId)
        name.text = contact.name
        phonenum.text = contact.phonenum
        email.text = contact.email

        return view
    }
}