package com.example.myapplication2.data.model

data class OpenLibraryResponse(
    val numFound: Int,
    val docs: List<ApiItem> // <-- docs here
)

data class ApiItem(
    val id: Int,
    val name: String,

)