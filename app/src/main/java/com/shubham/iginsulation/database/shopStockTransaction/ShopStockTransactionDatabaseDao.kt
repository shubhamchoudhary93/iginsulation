package com.shubham.iginsulation.database.shopStockTransaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShopStockTransactionDatabaseDao {
    @Insert
    fun insert(transaction: ShopStockTransaction)

    @Update
    fun update(transaction: ShopStockTransaction)

    @Query("SELECT * from shop_stock_transaction_data_table WHERE id = :id")
    fun get(id: Long): ShopStockTransaction?

    @Query("DELETE FROM shop_stock_transaction_data_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM shop_stock_transaction_data_table")
    fun clear()

    @Query("SELECT * FROM shop_stock_transaction_data_table ORDER BY id DESC")
    fun getList(): List<ShopStockTransaction>

    @Query("SELECT * FROM shop_stock_transaction_data_table ORDER BY id DESC LIMIT 1")
    fun getLastShopStockTransaction(): ShopStockTransaction
}