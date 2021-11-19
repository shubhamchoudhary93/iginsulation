package com.shubham.iginsulation.database.customer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_data_table")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "customer_name")
    var name: String = "",

    @ColumnInfo(name = "customer_phone")
    var phone: Long = 0L,

    @ColumnInfo(name = "customer_address")
    var address: String = "",

    @ColumnInfo(name = "customer_opening_balance")
    var opening: Float = 0F,

    @ColumnInfo(name = "customer_current_balance")
    var current: Float = 0F
)