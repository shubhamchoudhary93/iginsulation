package com.shubham.iginsulation.database.shopStockTransaction

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopStockTransaction::class], version = 1, exportSchema = false)
abstract class ShopStockTransactionDatabase : RoomDatabase() {

    abstract val shopStockTransactionDatabaseDao: ShopStockTransactionDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ShopStockTransactionDatabase? = null

        fun getInstance(context: Context): ShopStockTransactionDatabase {
            synchronized(this) {

                var instance =
                    INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShopStockTransactionDatabase::class.java,
                        "shop_stock_transaction_data_table"
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