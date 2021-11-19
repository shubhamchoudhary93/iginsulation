package com.shubham.iginsulation.database.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shubham.iginsulation.database.customer.Customer

@Dao
interface TransactionDatabaseDao {
    @Insert
    fun insert(transaction: Transaction)

    @Update
    fun update(transaction: Transaction)

    @Query("SELECT * from transaction_data_table WHERE id = :id")
    fun get(id: Long): Transaction?

    @Query("DELETE FROM transaction_data_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM transaction_data_table")
    fun clear()

    @Query("SELECT * FROM transaction_data_table ORDER BY id DESC")
    fun getList(): List<Transaction>

    @Query("SELECT * FROM transaction_data_table ORDER BY id DESC LIMIT 1")
    fun getLastTransaction(): Transaction

    @Query("SELECT * FROM transaction_data_table WHERE transaction_customer = :customer ORDER BY id DESC LIMIT 2")
    fun getLastTwoTransaction(customer : String): List<Transaction>

    @Query("DELETE FROM transaction_data_table WHERE transaction_date = :date AND transaction_customer = :customer AND transaction_amount = :amount")
    fun deleteCashTransaction(date: String, amount: Int, customer: String)
}