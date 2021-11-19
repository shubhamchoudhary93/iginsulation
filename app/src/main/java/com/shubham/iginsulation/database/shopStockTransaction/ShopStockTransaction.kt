package com.shubham.iginsulation.database.shopStockTransaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_stock_transaction_data_table")
data class ShopStockTransaction(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "transaction_add")
    var add: Boolean = false,

    @ColumnInfo(name = "transaction_stock")
    var stock: String = "",

    @ColumnInfo(name = "transaction_quantity")
    var quantity: Int = 0,

    @ColumnInfo(name = "transaction_date")
    var date: String = "23/04/2021",

    @ColumnInfo(name = "transaction_detail")
    var detail: String = ""
)