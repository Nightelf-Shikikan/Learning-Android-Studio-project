package com.example.myapplication2.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogItem(
    val id: Int,
    val name: String,
    val details: String,
    val price: Double
) : Parcelable