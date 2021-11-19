package com.shubham.iginsulation.database.customer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerDatabaseDao {
    @Insert
    fun insert(customer: Customer)

    @Update
    fun update(customer: Customer)

    @Query("SELECT * from customer_data_table WHERE id = :id")
    fun get(id: Long): Customer?

    @Query("DELETE FROM customer_data_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM customer_data_table")
    fun clear()

    @Query("SELECT * FROM customer_data_table ORDER BY id DESC LIMIT 1")
    fun getLastCustomer(): Customer

    @Query("SELECT * from customer_data_table ORDER BY customer_name")
    fun getList(): List<Customer>

    @Query("SELECT DISTINCT customer_name from customer_data_table ORDER BY customer_name")
    fun getCustomerNames(): List<String>

    @Query("SELECT customer_current_balance FROM customer_data_table  WHERE customer_name = :searchText")
    fun getCustomerCurrentBalance(searchText: String): Float

    @Query("UPDATE customer_data_table SET customer_current_balance = :newValue WHERE customer_name = :searchText")
    fun setCustomerCurrentBalance(newValue: Float, searchText: String): Int

    @Query("SELECT customer_name from customer_data_table WHERE customer_name = :searchText")
    fun checkCustomer(searchText: String): List<String>
}