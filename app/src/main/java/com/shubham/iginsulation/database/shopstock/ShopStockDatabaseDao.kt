package com.shubham.iginsulation.database.shopstock

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShopStockDatabaseDao {
    @Insert
    fun insert(stock: ShopStock)

    @Update
    fun update(stock: ShopStock)

    @Query("SELECT * from shop_stock_data_table WHERE id = :id")
    fun get(id: Long): ShopStock?

    @Query("SELECT * from shop_stock_data_table WHERE name = :name")
    fun get(name: String): ShopStock?

    @Query("DELETE FROM shop_stock_data_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM shop_stock_data_table")
    fun clear()

    @Query("SELECT * FROM shop_stock_data_table ORDER BY id DESC LIMIT 1")
    fun getLastShopStock(): ShopStock

    @Query("SELECT * from shop_stock_data_table ORDER BY name")
    fun getList(): List<ShopStock>

    @Query("SELECT * from shop_stock_data_table WHERE sub_category = :filter ORDER BY name")
    fun getListByFilter(filter: String): List<ShopStock>

    @Query("SELECT DISTINCT name from shop_stock_data_table ORDER BY name")
    fun getShopStockNames(): List<String>

    @Query("SELECT DISTINCT category FROM shop_stock_data_table ORDER BY category DESC")
    fun getAllCategory(): List<String>

    @Query("SELECT DISTINCT name FROM shop_stock_data_table ORDER BY name, category, sub_category DESC")
    fun getAllStock(): List<String>

    @Query("SELECT DISTINCT sub_category FROM shop_stock_data_table ORDER BY sub_category DESC")
    fun getAllSubCategory(): List<String>

    @Query("SELECT quantity FROM shop_stock_data_table  WHERE name = :searchText")
    fun getShopStockQuantity(searchText: String): Float

    @Query("UPDATE shop_stock_data_table SET quantity = :newValue WHERE name = :searchText")
    fun setShopStockQuantity(newValue: Float, searchText: String): Int

    @Query("SELECT name from shop_stock_data_table WHERE name = :searchText")
    fun checkShopStock(searchText: String): List<String>
}