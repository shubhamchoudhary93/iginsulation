package com.shubham.iginsulation.database.rate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Rate::class], version = 1, exportSchema = false)
abstract class RateDatabase : RoomDatabase() {

    abstract val rateDatabaseDao: RateDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: RateDatabase? = null

        fun getInstance(context: Context): RateDatabase {
            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RateDatabase::class.java,
                        "rate_data_table"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}