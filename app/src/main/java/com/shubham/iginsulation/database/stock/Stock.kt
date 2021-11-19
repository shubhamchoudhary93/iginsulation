package com.shubham.iginsulation.database.stock

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_data_table")
data class Stock(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "stock_name")
    var name: String = "",

    @ColumnInfo(name = "stock_category")
    var category: String = "",

    @ColumnInfo(name = "stock_sub_category")
    var subCategory: String = "",

    @ColumnInfo(name = "stock_rate")
    var rate: Float = 0F,

    @ColumnInfo(name = "stock_percentage")
    var percentage: Float = 0F,
)