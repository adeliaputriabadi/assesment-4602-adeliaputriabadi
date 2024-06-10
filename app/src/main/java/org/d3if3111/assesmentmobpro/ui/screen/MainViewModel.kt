package org.d3if3111.assesmentmobpro.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if3111.assesmentmobpro.model.Handphone
import org.d3if3111.assesmentmobpro.network.ApiStatus
import org.d3if3111.assesmentmobpro.network.HandphoneApi


class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Handphone>())
        private set
    var status = MutableStateFlow(ApiStatus.LOADING)
    init {
        retrieveData()
    }
    fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = HandphoneApi.service.getHandphone()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }
}