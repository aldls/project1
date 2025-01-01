package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _productList = MutableLiveData<MutableList<ProductInfo>>().apply {
        value= mutableListOf()
    }
    val productList: LiveData<MutableList<ProductInfo>> get() = _productList

    fun addProduct(product: ProductInfo) {
        val updatedList = _productList.value ?: mutableListOf()
        updatedList.add(product)
        _productList.value = updatedList
    }
}