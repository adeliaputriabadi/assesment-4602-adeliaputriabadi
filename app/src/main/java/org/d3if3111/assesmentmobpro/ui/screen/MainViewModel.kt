package org.d3if3111.assesmentmobpro.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3111.assesmentmobpro.database.UangDao
import org.d3if3111.assesmentmobpro.model.Uang

class MainViewModel(dao: UangDao) : ViewModel() {

    val data: StateFlow<List<Uang>> = dao.getUang().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}