package com.shubham.iginsulation.database.sale

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SaleDatabaseDao {
    @Insert
    fun insert(sale: Sale)

    @Update
    fun update(sale: Sale)

    @Query("SELECT * from sale_data_table WHERE id = :id")
    fun get(id: Long): Sale?

    @Query("DELETE FROM sale_data_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM sale_data_table")
    fun clear()

    @Query("SELECT * FROM sale_data_table ORDER BY id DESC")
    fun getAllSales(): LiveData<List<Sale>>

    @Query("SELECT * FROM sale_data_table ORDER BY id DESC")
    fun getList(): List<Sale>

    @Query("SELECT * FROM sale_data_table ORDER BY id DESC LIMIT 1")
    fun getLastSale(): Sale

    @Query("SELECT * FROM sale_data_table WHERE sale_date = :searchText")
    fun searchSale(searchText: String): LiveData<List<Sale>>

    @Query("SELECT id FROM sale_data_table ORDER BY id DESC LIMIT 1")
    fun getLastSaleID(): Long

    @Query("SELECT * FROM sale_data_table WHERE sale_customer = :searchText ORDER BY id DESC")
    fun searchSaleCustomer(searchText: String): List<Sale>

}