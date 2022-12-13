package com.example.features.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.domain.model.Item

@Composable
fun ItemScreen(viewModel: ItemViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn {
        Button(onClick = viewModel::insertItem) {
            Text("Insert Item")
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