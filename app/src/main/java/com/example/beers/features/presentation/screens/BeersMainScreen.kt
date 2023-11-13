package com.example.beers.features.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.beers.features.presentation.contract.BeerUiState
import com.example.beers.features.presentation.models.BeerUiModel

@Composable
fun BeersMainScreen(modifier: Modifier = Modifier, state: State<BeerUiState>){
    val beersList = state.value.beersList?.collectAsLazyPagingItems()
    LazyColumn{
        beersList?.let {beersList->
            items(count = beersList.itemCount){index->
                beersList[index]?.let { item -> BeerItem(item = item) }
            }
        }
        item {
            if( beersList?.loadState?.append == LoadState.Loading ){
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun BeerItem(item: BeerUiModel){
    Card(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = item.id.toString(), style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = item.tagLine)
        }
    }

}