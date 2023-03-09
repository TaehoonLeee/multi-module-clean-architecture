package com.example.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.features.gallery.GalleryScreen
import com.example.features.gallery.GalleryViewModel
import com.example.features.item.ItemScreen
import com.example.features.item.ItemViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BottomNavApp()
        }
    }
}

sealed class Direction(val route: String) {
    object Item : Direction("item")
    object Gallery : Direction("gallery")
    object Empty : Direction("empty")
}

@Composable
private fun BottomNavApp(
    itemViewModel: ItemViewModel = viewModel { ItemViewModel() },
    galleryViewModel: GalleryViewModel = viewModel { GalleryViewModel() }
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {

            }
        }
    ) {
        NavHost(modifier = Modifier.padding(it), navController = navController, startDestination = Direction.Gallery.route) {
            composable(Direction.Item.route) {
                ItemScreen(itemViewModel)
            }
            composable(Direction.Gallery.route) {
                GalleryScreen(galleryViewModel)
            }
            composable(Direction.Empty.route) {

            }
        }
    }
}

private fun navigate(navController: NavController, route: String) = navController.navigate(route) {
    launchSingleTop = true
    restoreState = true
    popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
    }
}
