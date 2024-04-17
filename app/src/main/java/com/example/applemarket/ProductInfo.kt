package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInfo(
    val image: Int,
    val title: Int,
    val subscription: Int,
    val seller: Int,
    val price: Int,
    val address: Int,
    val likeNum: Int,
    val chatNum: Int,
    ) : Parcelable
