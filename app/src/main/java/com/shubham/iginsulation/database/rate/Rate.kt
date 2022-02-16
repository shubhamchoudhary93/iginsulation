package com.shubham.iginsulation.database.rate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rate_data_table")
data class Rate(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "bill_cost_price")
    var billCostPrice: Float = 0F,

    @ColumnInfo(name = "cost_price")
    var costPrice: Float = 0F,

    @ColumnInfo(name = "bill_sell_price")
    var billSellPrice: Float = 0F,

    @ColumnInfo(name = "sell_price")
    var sellPrice: Float = 0F,

    @ColumnInfo(name = "seller")
    var seller: String = "",

    @ColumnInfo(name = "date")
    var date: String = ""
)