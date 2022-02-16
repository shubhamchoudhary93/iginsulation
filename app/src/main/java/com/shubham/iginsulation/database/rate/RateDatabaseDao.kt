package com.shubham.iginsulation.database.rate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RateDatabaseDao {
    @Insert
    fun insert(rate: Rate)

    @Update
    fun update(rate: Rate)

    @Query("SELECT * from rate_data_table WHERE id = :id")
    fun get(id: Long): Rate?

    @Query("DELETE FROM rate_data_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM rate_data_table")
    fun clear()

    @Query("SELECT * FROM rate_data_table ORDER BY id DESC LIMIT 1")
    fun getLastRate(): Rate

    @Query("SELECT * from rate_data_table ORDER BY name")
    fun getList(): List<Rate>

    @Query("SELECT name from rate_data_table WHERE name = :searchText")
    fun checkRate(searchText: String): List<String>

    @Query("SELECT * from rate_data_table WHERE name = :id")
    fun get(id: String): Rate?
}