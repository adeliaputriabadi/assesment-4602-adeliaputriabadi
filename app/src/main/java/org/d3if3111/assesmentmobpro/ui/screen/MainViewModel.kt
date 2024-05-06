package org.d3if3111.assesmentmobpro.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3111.assesmentmobpro.model.Uang

class MainViewModel : ViewModel() {

    val data = getDataDummy()
    private fun getDataDummy(): List<Uang> {
        val keterangan = arrayOf(
            "Beli Kuota",
            "Penghasilan dari tiktok",
            "Beli makan",
            "Pendapatan penjualan shopee",
            "Beli baju",
            "Beli Kuota",
            "Penghasilan dari tiktok",
            "Beli makan",
            "Pendapatan penjualan shopee",
            "Beli baju"

        )
        val nominal = arrayOf(
            "30000",
            "1000000",
            "20000",
            "500000",
            "300000",
            "30000",
            "1000000",
            "20000",
            "500000",
            "300000",

        )
        val kategori = arrayOf(
            "Pengeluaran",
            "Pendapatan",
            "Pengeluaran",
            "Pendapatan",
            "Pengeluaran",
            "Pengeluaran",
            "Pendapatan",
            "Pengeluaran",
            "Pendapatan",
            "Pengeluaran"
        )
        val data = mutableListOf<Uang>()
        for (i in keterangan.indices) {
            data.add(
                Uang(
                    (i + 1).toLong(),
                    keterangan[i],
                    nominal[i],
                    kategori[i]
                )
            )
        }
        return data
    }
}