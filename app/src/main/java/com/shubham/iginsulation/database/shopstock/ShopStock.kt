package com.shubham.iginsulation.database.shopstock

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_stock_data_table")
data class ShopStock(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "category")
    var category: String = "",

    @ColumnInfo(name = "sub_category")
    var subCategory: String = "",

    @ColumnInfo(name = "quantity")
    var quantity: Int = 0,

    @ColumnInfo(name = "min_quantity")
    var minQuantity: Int = 0,

    @ColumnInfo(name = "default_reduce")
    var defaultReduce: Int = 0,

    @ColumnInfo(name = "rate")
    var rate: Float = 0F,

    @ColumnInfo(name = "seller")
    var seller: String = "",
)