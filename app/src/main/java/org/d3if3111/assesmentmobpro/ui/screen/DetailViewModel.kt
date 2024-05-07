package org.d3if3111.assesmentmobpro.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3111.assesmentmobpro.database.UangDao
import org.d3if3111.assesmentmobpro.model.Uang
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: UangDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(title: String, rupiah: String, selectedKategori: String ) {
        val uang = Uang(
            tanggal = formatter.format(Date()),
            keterangan = title,
            nominal = rupiah,
            kategori = selectedKategori
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(uang)
        }
    }
    suspend fun getUang(id: Long): Uang? {
        return dao.getUangById(id)
    }

    fun update(id: Long, title: String, rupiah: String, selectedKategori: String) {
        val uang = Uang(
            id = id,
            tanggal = formatter.format(Date()),
            keterangan = title,
            nominal = rupiah,
            kategori = selectedKategori
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(uang)
        }
    }
}