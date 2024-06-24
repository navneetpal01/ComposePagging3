package com.example.composepagging3.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.composepagging3.domain.Beer
import kotlin.time.Duration


@Composable
fun BeerScreen(
    beers : LazyPagingItems<Beer>
){
    val context = LocalContext.current
    LaunchedEffect(key1 = beers.loadState) {
        if (beers.loadState.refresh is LoadState.Error){
            Toast.makeText(context,"Error: " + (beers.loadState.refresh as LoadState.Error)?.error?.message,Toast.LENGTH_LONG).show()
        }

    }






}