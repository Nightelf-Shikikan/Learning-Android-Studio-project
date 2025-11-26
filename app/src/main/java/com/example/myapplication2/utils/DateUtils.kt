package com.example.myapplication2.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getCurrentDate(): String {
    val current = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return current.format(formatter)
}
