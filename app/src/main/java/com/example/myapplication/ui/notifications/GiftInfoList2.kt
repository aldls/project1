package com.example.myapplication.data

import com.example.myapplication.R

data class GiftItem2(
    val name: String,
    val price: String,
    val link: String,
    val imageRes: Int
)

val giftInfoList2 = listOf(
    GiftItem2("초콜릿", "10,000원", "https://www.nespresso.com/kr/ko/order/accessories/vertuo/milk-chocolate?gad_source=4&gclid=Cj0KCQiA7NO7BhDsARIsADg_hIZN8rSiXgdjIlE9cVb0_uH6NYK-uIWwuCzIqOdGPKBFNYiKLxOKa28aAnRZEALw_wcB&gclsrc=aw.ds", R.drawable.chocolate),
    GiftItem2("꽃다발", "15,000원", "https://www.kurly.com/goods/1000305828", R.drawable.flower),
    GiftItem2("책", "20,000원", "https://product.kyobobook.co.kr/detail/S000214929934", R.drawable.book),
    GiftItem2("문화상품권", "5,000원", "https://www.culturegift.co.kr/", R.drawable.money),
    GiftItem2("테디베어", "25,000원", "https://m.toytalez.com/product/detail.html?product_no=27", R.drawable.bear),
    GiftItem2("향수", "50,000원", "https://www.dior.com/ko_kr/beauty/products/%EB%AF%B8%EC%8A%A4-%EB%94%94%EC%98%AC-%EC%98%A4-%EB%93%9C-%ED%8D%BC%ED%93%B8-C099600762.html?gad_source=1&gclid=Cj0KCQiA7NO7BhDsARIsADg_hIYLpUWfsMUgGy6hQOYZUE-EcRiH9WvFQdk4gbgf53m0qX4ie7uidbEaApWSEALw_wcB", R.drawable.perfume),
    GiftItem2("스카프", "30,000원", "https://kr.burberry.com/check-cashmere-scarf-p80778811", R.drawable.scarf),
    GiftItem2("신발", "70,000원", "https://www.nike.com/kr/t/c1ty-%EC%A3%BC%EB%8B%88%EC%96%B4-%EC%8B%A0%EB%B0%9C-PPhlA2Nq/HQ0028-600", R.drawable.shoes),
    GiftItem2("시계", "100,000원", "https://www.vacheron-constantin.com/kr/ko/collections/traditionnelle/6057t-000g-h067.html?_gl=1%2a3rg1m%2a_up%2aMQ..%2a_gs%2aMQ..&gclid=Cj0KCQiA7NO7BhDsARIsADg_hIblm4vJoXgunWWc6gcebnPulPN7xcT3FhsrIaipk6p3ItFroVCzMbIaAs8HEALw_wcB&gclsrc=aw.ds", R.drawable.watch)
)
