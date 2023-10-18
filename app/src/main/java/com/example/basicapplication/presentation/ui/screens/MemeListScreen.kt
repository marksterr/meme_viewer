package com.example.basicapplication.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.basicapplication.R
import com.example.basicapplication.data.MemeItem
import com.example.basicapplication.data.Memes
import com.example.basicapplication.data.UIState
import com.example.basicapplication.presentation.viewmodel.MemeViewModel

@Composable
fun MemeListHome(viewModel: MemeViewModel, navController: NavController) {

    val stateData = viewModel.data.observeAsState().value
    stateData?.let {
        when (it) {
            is UIState.Success -> {
                MemeList(data = it.data.data.memes, detailScreen = {
                    navController.navigate("memeScreen/$it")
                })
            }
            is UIState.Failure -> MemeError(it.errorMessage)
        }
    }
}

@Composable
fun MemeError(errorMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.error_message, errorMessage))
    }
}

@Composable
fun MemeList(data: List<MemeItem>, detailScreen: (MemeItem)-> Unit) {
    LazyColumn {
        items(data) {
            MemeItemComposable(memeItem = it, detailScreen = detailScreen)
        }
    }
}

@Composable
fun MemeItemComposable(memeItem: MemeItem, detailScreen: (MemeItem)-> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        shape = RoundedCornerShape(12.dp)) {
        Column(modifier = Modifier
            .padding(10.dp)
            .clickable { detailScreen(memeItem) }) {
            Text(text = stringResource(id = R.string.name_meme, memeItem.name))
            Text(text = stringResource(id = R.string.width_meme, memeItem.width))
            Text(text = stringResource(id = R.string.height_meme, memeItem.height))
            MemeImage(memeUrl = memeItem.url)
        }
    }
}

@Preview
@Composable
fun PrevMemeItem() {
    val prevData = MemeItem(
        name = "Drake Hotline",
        width = 1200,
        height = 1200,
        url = "https://i.imgflip.com/1bij.jpg"
    )
    MemeItemComposable(
        memeItem = prevData,
        detailScreen = {}
    )
}