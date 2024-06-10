package org.d3if3111.assesmentmobpro.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3111.assesmentmobpro.model.Handphone
import org.d3if3111.assesmentmobpro.network.HandphoneApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Handphone>())
        private set
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = HandphoneApi.service.getHandphone()
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}