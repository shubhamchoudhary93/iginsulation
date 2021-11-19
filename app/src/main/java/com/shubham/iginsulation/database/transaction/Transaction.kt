package com.shubham.iginsulation.database.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_data_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "transaction_receipt")
    var receipt: Boolean = true,

    @ColumnInfo(name = "transaction_customer")
    var name: String = "",

    @ColumnInfo(name = "transaction_amount")
    var amount: Int = 0,

    @ColumnInfo(name = "transaction_date")
    var date: String = "23/04/2021",

    @ColumnInfo(name = "transaction_detail")
    var detail: String = ""
)