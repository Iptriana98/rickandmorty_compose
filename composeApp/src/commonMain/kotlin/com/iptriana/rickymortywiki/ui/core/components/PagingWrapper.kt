package com.iptriana.rickymortywiki.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems

enum class PagingType {
    ROW,
    COLUMN,
    VERTICAL_GRID
}

@Composable
fun <T : Any> PagingWrapper(
    pagingItems: LazyPagingItems<T>,
    initialView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    extraItemsView: @Composable () -> Unit = {},
    itemView: @Composable (T) -> Unit,
    pagingType: PagingType
){
    when {
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> {
            // Initial loading
            initialView()
        }
        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            // No data
            emptyView()
        }
        else -> {
            // Content
            when(pagingType){
                PagingType.ROW -> {
                    LazyRow {
                        items(pagingItems.itemCount) { index ->
                            pagingItems[index]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }
                PagingType.VERTICAL_GRID -> {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(pagingItems.itemCount) { index ->
                            pagingItems[index]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }
                PagingType.COLUMN -> {
                    LazyColumn {
                        items(pagingItems.itemCount) { index ->
                            pagingItems[index]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }
            }

            if (pagingItems.loadState.append is LoadState.Loading) {
                extraItemsView()
            }
        }
    }
}