package com.example.basicapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MemesResponse(
    val data: Memes
)

data class Memes(
    val memes: List<MemeItem>
)

@Parcelize
data class MemeItem(
    val name: String,
    val width: Int,
    val height: Int,
    val url: String
): Parcelable
