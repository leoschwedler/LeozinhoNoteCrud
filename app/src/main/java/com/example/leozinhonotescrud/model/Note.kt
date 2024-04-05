package com.example.leozinhonotescrud.model

import java.io.Serializable

data class Note(
    val idNote: Int,
    val description: String,
    val dateRegister: String
): Serializable
