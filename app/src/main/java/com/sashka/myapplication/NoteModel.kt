package com.sashka.myapplication

data class NoteModel(
    val title: String,
    val desc: String ,
    val date: String ,
    val imageUri: String? = null
): java.io.Serializable
