package org.d3if3111.assesmentmobpro.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "uang")
data class Uang(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val keterangan: String,
    val nominal: String,
    val kategori: String,
    val tanggal: String
)
