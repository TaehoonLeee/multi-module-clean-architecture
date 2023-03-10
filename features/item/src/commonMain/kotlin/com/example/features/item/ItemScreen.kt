package com.example.features.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.domain.model.Item

@Composable
fun ItemScreen(itemViewModel: ItemViewModel) {
    val uiState by itemViewModel.uiState.collectAsState()

    LazyColumn {
        item {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = itemViewModel::insertItem) {
                    Text("Insert Item")
                }
                Button(onClick = itemViewModel::clear) {
                    Text("Clear Item")
                }
            }
        }

        items(uiState) {
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