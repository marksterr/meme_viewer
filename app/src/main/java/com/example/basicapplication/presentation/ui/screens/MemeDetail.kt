package com.example.basicapplication.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.basicapplication.data.MemeItem

import com.example.basicapplication.R

@Composable
fun MemeDetail(data: MemeItem) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.name_meme, data.name)
        )
        MemeImage(memeUrl = data.url)
    }
}

@Composable
fun MemeImage(memeUrl: String) {
    AsyncImage(
        model = memeUrl,
        contentDescription = stringResource(id = R.string.content_description)
    )
}

@Preview
@Composable
fun PrevMemeDetail() {
    val prevData = MemeItem(
        name = "One Does Not Simply",
        width = 568,
        height = 335,
        url = "https://i.imgflip.com/1bij.jpg"
    )
    MemeDetail(data = prevData)
}