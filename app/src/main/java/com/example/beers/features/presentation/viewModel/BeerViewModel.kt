package com.example.beers.features.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.beers.features.domain.usecases.GetBeersUsecase
import com.example.beers.features.presentation.contract.BeerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val beersUsecase: GetBeersUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BeerUiState())
    val uiState = _uiState.asStateFlow()

    suspend fun getBeers(){
        val beersList = beersUsecase.getBeers().cachedIn(viewModelScope)
        _uiState.update {
            it.copy(beersList = beersList)
        }
    }
}