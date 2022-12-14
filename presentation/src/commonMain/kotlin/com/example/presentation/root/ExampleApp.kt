package com.example.presentation.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
            "item" to Icons.Default.List,
            "gallery" to Icons.Default.Search,
            "empty" to Icons.Default.Person
        )
        tabs.forEach { (route, icon) ->
            BottomNavigationItem(
                icon = {
                    Image(
                        imageVector = icon,
                        contentDescription = null
                    )
                },
                onClick = { rootComponent.navigate(route) },
                selected = rootComponent.childStack.active.instance == navBackStackEntry
            )
        }
    }
}