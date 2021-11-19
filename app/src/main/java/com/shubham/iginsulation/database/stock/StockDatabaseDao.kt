package com.shubham.iginsulation.database.stock

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubham.iginsulation.database.customer.Customer

@Dao
interface StockDatabaseDao {
    @Insert
    fun insert(stock: Stock)

    @Update
    fun update(stock: Stock)

    @Query("SELECT * from stock_data_table WHERE id = :id")
    fun get(id: Long): Stock?

    @Query("SELECT * from Stock_data_table WHERE stock_name = :name")
    fun get(name: String): Stock?

    @Query("DELETE FROM stock_data_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM stock_data_table")
    fun clear()

    @Query("SELECT * FROM stock_data_table ORDER BY id DESC LIMIT 1")
    fun getLastStock(): Stock

    @Query("SELECT * from stock_data_table ORDER BY stock_name")
    fun getList(): List<Stock>

    @Query("SELECT DISTINCT stock_name from stock_data_table ORDER BY stock_name")
    fun getStockNames(): List<String>

    @Query("SELECT DISTINCT stock_category FROM stock_data_table ORDER BY stock_category DESC")
    fun getAllCategory(): List<String>

    @Query("SELECT DISTINCT stock_sub_category FROM stock_data_table ORDER BY stock_sub_category DESC")
    fun getAllSubCategory(): List<String>
}