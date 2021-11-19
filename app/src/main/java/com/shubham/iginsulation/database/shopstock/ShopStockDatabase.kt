package com.shubham.iginsulation.database.shopstock

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopStock::class], version = 1, exportSchema = false)
abstract class ShopStockDatabase : RoomDatabase() {

    abstract val shopStockDatabaseDao: ShopStockDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ShopStockDatabase? = null

        fun getInstance(context: Context): ShopStockDatabase {
            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShopStockDatabase::class.java,
                        "shop_stock_data_table"
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