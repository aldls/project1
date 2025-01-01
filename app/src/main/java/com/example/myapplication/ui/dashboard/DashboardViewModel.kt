package com.example.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    val imageList = mutableListOf<Pair<String, String>>()

    init {
        preloadImages()
    }

    private fun preloadImages() {
        val preloadedImages = listOf(
            Pair("android.resource://com.example.myapplication/drawable/image13", "Sample Text 1"),
            Pair("android.resource://com.example.myapplication/drawable/image14", "Sample Text 2"),
            Pair("android.resource://com.example.myapplication/drawable/image15", "Sample Text 3"),
            Pair("android.resource://com.example.myapplication/drawable/image16", "Sample Text 4"),
            Pair("android.resource://com.example.myapplication/drawable/image17", "Sample Text 5"),
            Pair("android.resource://com.example.myapplication/drawable/image18", "Sample Text 6"),
            Pair("android.resource://com.example.myapplication/drawable/image19", "Sample Text 7"),
            Pair("android.resource://com.example.myapplication/drawable/image20", "Sample Text 8"),
            Pair("android.resource://com.example.myapplication/drawable/image21", "Sample Text 9"),
            Pair("android.resource://com.example.myapplication/drawable/image22", "Sample Text 10")
        )
        imageList.addAll(preloadedImages)
    }

}