package com.example.zolo.model

import androidx.annotation.DrawableRes

data class Book(
    /*
        Book Name (String)
        Book Author (String)
        Available for borrowing till (DateTime)
        Genre (Array of String)
    */

    val name: String,
    val author: String,
    val availableTill: String,
    val genre: List<String>,
    val bookDescription: String
)
