package com.shubham.iginsulation.database.saledetails

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubham.iginsulation.database.saledetails.SaleDetails

@Dao
interface SaleDetailsDatabaseDao {
    @Insert
    fun insert(saleDetails: SaleDetails)

    @Update
    fun update(saleDetails: SaleDetails)

    @Query("SELECT * from sale_details_data_table WHERE id = :id")
    fun get(id: Long): SaleDetails?

    @Query("DELETE FROM sale_details_data_table")
    fun clear()

    @Query("DELETE FROM sale_details_data_table WHERE id = :id")
    fun delete(id: Long)

    @Query("SELECT * FROM sale_details_data_table ORDER BY id DESC")
    fun getAllSaleDetails(): LiveData<List<SaleDetails>>

    @Query("SELECT * FROM sale_details_data_table ORDER BY id DESC LIMIT 1")
    fun getLastSaleDetails(): SaleDetails

    @Query("SELECT sale_details_total FROM sale_details_data_table")
    fun getTotals(): List<Int>

    @Query("SELECT id FROM sale_details_data_table ORDER BY id DESC LIMIT 1")
    fun getLastSaleDetailsID(): Long

    @Query("UPDATE sale_details_data_table SET sale_details_sale_id = :saleId WHERE sale_details_sale_id =  :defaultId")
    fun updateSaleDetailsId(saleId: Long, defaultId: Long)

    @Query("UPDATE sale_details_data_table SET sale_details_customer = :newCustomer WHERE sale_details_sale_id =  :saleId")
    fun updateSaleDetailsCustomer(saleId: Long, newCustomer: String)

    @Query("SELECT * FROM sale_details_data_table WHERE sale_details_sale_id = :saleId ORDER BY id DESC")
    fun getSaleDetails(saleId: Long): LiveData<List<SaleDetails>>

    @Query("SELECT * FROM sale_details_data_table WHERE sale_details_sale_id = :saleId ORDER BY id DESC")
    fun getSaleDetails1(saleId: Long): List<SaleDetails>

    @Query("DELETE FROM sale_details_data_table WHERE sale_details_sale_id = :saleId")
    fun deleteSaleId(saleId: Long)
}