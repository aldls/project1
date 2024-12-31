package com.example.myapplication.data

import com.example.myapplication.R

data class GiftItem(
    val name: String,
    val price: String,
    val link: String,
    val imageRes: Int
)

val giftInfoList = listOf(
    GiftItem("초콜릿", "10,000원", "https://www.example.com/chocolate", R.drawable.addicon),
    GiftItem("꽃다발", "15,000원", "https://www.example.com/flowers", R.drawable.gift),
    GiftItem("책", "20,000원", "https://www.example.com/book", R.drawable.openedgift),
    GiftItem("쿠폰", "5,000원", "https://www.example.com/coupon", R.drawable.humanicon),
    GiftItem("테디베어", "25,000원", "https://www.example.com/teddy", R.drawable.pulsicon),
    GiftItem("향수", "50,000원", "https://www.example.com/perfume", R.drawable.openedgift),
    GiftItem("스카프", "30,000원", "https://www.example.com/scarf", R.drawable.addicon),
    GiftItem("신발", "70,000원", "https://www.example.com/shoes", R.drawable.humanicon),
    GiftItem("시계", "100,000원", "https://www.example.com/watch", R.drawable.pulsicon)
)
