package org.d3if3111.assesmentmobpro.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3111.assesmentmobpro.database.UangDao
import org.d3if3111.assesmentmobpro.ui.screen.DetailViewModel
import org.d3if3111.assesmentmobpro.ui.screen.MainViewModel

class ViewModelFactory(
    private val dao: UangDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}