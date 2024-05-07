package org.d3if3111.assesmentmobpro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3111.assesmentmobpro.model.Uang

@Database(entities = [Uang::class], version = 1, exportSchema = false)
abstract class UangDb : RoomDatabase() {

    abstract val dao: UangDao

    companion object {

        @Volatile
        private var INSTANCE: UangDb? = null

        fun getInstance(context: Context): UangDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UangDb::class.java,
                        "uang.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
