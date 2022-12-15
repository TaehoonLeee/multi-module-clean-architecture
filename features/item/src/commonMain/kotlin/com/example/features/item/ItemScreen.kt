package com.example.features.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.example.domain.model.Item

@Composable
fun ItemScreen(itemComponent: ItemComponent) {
    val uiState by itemComponent.state.subscribeAsState()

    LazyColumn {
        item {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = itemComponent::onInsertItem) {
                    Text("Insert Item")
                }
                Button(onClick = itemComponent::onClearItem) {
                    Text("Clear Item")
                }
            }
        }

        items(uiState.items) {
            ItemCard(it)
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    Row {
        Text(item.title)
        Text(item.description)
    }
}