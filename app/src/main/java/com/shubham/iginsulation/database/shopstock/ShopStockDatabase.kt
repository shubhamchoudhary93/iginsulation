package com.shubham.iginsulation.database.shopstock

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [ShopStock::class], version = 2, exportSchema = false)
abstract class ShopStockDatabase : RoomDatabase() {

    abstract val shopStockDatabaseDao: ShopStockDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ShopStockDatabase? = null

        fun getInstance(context: Context): ShopStockDatabase {
            synchronized(this) {

                val MIGRATION_1_2: Migration = object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE shop_stock_data_table ADD COLUMN opening INTEGER NOT NULL DEFAULT 0")
                        database.execSQL("ALTER TABLE shop_stock_data_table ADD COLUMN opening_date TEXT NOT NULL DEFAULT '23/04/2021'")
                    }
                }

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShopStockDatabase::class.java,
                        "shop_stock_data_table"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_1_2)
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}