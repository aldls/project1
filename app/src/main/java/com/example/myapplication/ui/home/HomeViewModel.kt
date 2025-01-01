package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _productList = MutableLiveData<MutableList<ProductInfo>>().apply {
        value = mutableListOf(
            ProductInfo("Red Shoes", "11,000", "www.naver.com", "humanicon"),
            ProductInfo("Blue Shoes", "12,000", "www.naver.com", "humanicon"),
            ProductInfo("Red Pants", "43,000", "www.naver.com", "humanicon"),
            ProductInfo("Jacket", "11,000", "www.naver.com", "humanicon"),
            ProductInfo("Pink Shorts", "16,000", "www.naver.com", "humanicon")
        )
    }
    val productList: LiveData<MutableList<ProductInfo>> get() = _productList

    fun addProduct(product: ProductInfo) {
        val updatedList = _productList.value ?: mutableListOf()
        updatedList.add(product)
        _productList.value = updatedList
    }
}