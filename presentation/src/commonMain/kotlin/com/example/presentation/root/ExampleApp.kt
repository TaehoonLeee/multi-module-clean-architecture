package com.example.presentation.root

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.router.stack.active
import com.example.features.gallery.GalleryScreen
import com.example.features.item.ItemScreen

@Composable
@OptIn(ExperimentalDecomposeApi::class)
fun ExampleApp(rootComponent: RootComponent) {
    Scaffold(
        bottomBar = {
           BottomBar(rootComponent)
        }
    ) {
        Children(rootComponent.childStack) {
            when (val child = it.instance) {
                is RootComponent.Child.Item -> ItemScreen(child.component)
                is RootComponent.Child.Gallery -> GalleryScreen(child.component)
                is RootComponent.Child.Empty -> Spacer(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun BottomBar(rootComponent: RootComponent) {
    BottomNavigation {
        val navBackStackEntry = rootComponent.childStack.active.instance
        val tabs = listOf(
            "item", "gallery", "empty"
        )
        tabs.forEach {
            BottomNavigationItem(
                selected = rootComponent.childStack.active.instance == navBackStackEntry,
                onClick = {

                }
            )
        }
    }
}