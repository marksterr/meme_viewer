package com.example.basicapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.basicapplication.data.MemeItem
import com.example.basicapplication.presentation.ui.MemeDestinations
import com.example.basicapplication.presentation.ui.screens.MemeDetail
import com.example.basicapplication.presentation.ui.screens.MemeListHome
import com.example.basicapplication.presentation.ui.theme.BasicApplicationTheme
import com.example.basicapplication.presentation.viewmodel.MemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BasicApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MemeViewModel = viewModel()
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = MemeDestinations.HOME.path){
                        composable(route = MemeDestinations.HOME.path) {
                            MemeListHome(viewModel = viewModel, navController = navController)
                        }
                        composable(
                            route = MemeDestinations.DETAILS.path,
                            arguments = listOf(navArgument(MemeDestinations.DETAILSARGS.path) {
                                type = NavType.ParcelableType(MemeItem::class.java)
                            })
                        ) {
                            MemeDetail(
                                data = it.arguments?.
                                getParcelable(MemeDestinations.DETAILSARGS.path) ?:
                                throw Exception("Random Error"))
                        }
                    }
                }
            }
        }
    }
}
