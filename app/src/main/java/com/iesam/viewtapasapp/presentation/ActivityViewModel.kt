package com.iesam.viewtapasapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.viewtapasapp.app.ErrorApp
import com.iesam.viewtapasapp.domain.GetTapaUseCase
import com.iesam.viewtapasapp.domain.Tapa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActivityViewModel (private val getTapaUseCase: GetTapaUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState

    fun loadTapa (){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO){
            //delay(5000)
            getTapaUseCase().fold(
                {responseError(it)},
                {responseSucess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp){
        _uiState.postValue(UiState(errorApp = errorApp, isLoading = false))
    }

    private fun responseSucess (tapa: Tapa){
        _uiState.postValue(UiState(tapa = tapa, isLoading = false))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val tapa : Tapa? = null
    )
}