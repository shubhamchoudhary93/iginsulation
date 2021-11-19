package com.shubham.iginsulation.database.saledetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sale_details_data_table")
data class SaleDetails(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "sale_details_sale_id")
    var saleId: Long = 0L,

    @ColumnInfo(name = "sale_details_customer")
    var customer: String = "",

    @ColumnInfo(name = "sale_details_item")
    var item: String = "",

    @ColumnInfo(name = "sale_details_quantity")
    var quantity: Float = 0.0F,

    @ColumnInfo(name = "sale_details_rate")
    var rate: Float = 0.0F,

    @ColumnInfo(name = "sale_details_percentage")
    var percentage: Float = 0.0F,

    @ColumnInfo(name = "sale_details_total")
    var total: Int = 0
)