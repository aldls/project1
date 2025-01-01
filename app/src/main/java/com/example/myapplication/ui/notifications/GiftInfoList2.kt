package com.example.myapplication.data

import com.example.myapplication.R

data class GiftItem2(
    val name: String,
    val price: String,
    val link: String,
    val imageRes: Int
)

val giftInfoList2 = listOf(
    GiftItem2("초콜릿", "10,000원", "https://www.example.com/chocolate", R.drawable.addicon),
    GiftItem2("꽃다발", "15,000원", "https://www.example.com/flowers", R.drawable.gift),
    GiftItem2("책", "20,000원", "https://www.example.com/book", R.drawable.openedgift),
    GiftItem2("쿠폰", "5,000원", "https://www.example.com/coupon", R.drawable.humanicon),
    GiftItem2("테디베어", "25,000원", "https://www.example.com/teddy", R.drawable.pulsicon),
    GiftItem2("향수", "50,000원", "https://www.example.com/perfume", R.drawable.openedgift),
    GiftItem2("스카프", "30,000원", "https://www.example.com/scarf", R.drawable.addicon),
    GiftItem2("신발", "70,000원", "https://www.example.com/shoes", R.drawable.humanicon),
    GiftItem2("시계", "100,000원", "https://www.example.com/watch", R.drawable.pulsicon)
)
