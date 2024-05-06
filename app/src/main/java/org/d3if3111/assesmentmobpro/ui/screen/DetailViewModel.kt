package org.d3if3111.assesmentmobpro.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3111.assesmentmobpro.model.Uang

class DetailViewModel : ViewModel() {
    private val mainViewModel = MainViewModel ()
    fun getUang(id: Long): Uang? {
        return mainViewModel.data.find { it.id == id }
    }
}