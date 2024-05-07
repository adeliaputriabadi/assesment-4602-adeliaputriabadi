package org.d3if3111.assesmentmobpro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.d3if3111.assesmentmobpro.model.Uang
import kotlinx.coroutines.flow.Flow

@Dao
interface UangDao {

    @Insert
    suspend fun insert(uang: Uang)

    @Update
    suspend fun update(uang: Uang)

    @Query("SELECT * FROM uang ORDER BY tanggal DESC")
    fun getUang(): Flow<List<Uang>>
}