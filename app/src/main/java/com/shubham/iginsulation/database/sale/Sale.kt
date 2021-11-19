package com.shubham.iginsulation.database.sale

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "sale_data_table")
data class Sale(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "sale_cash")
    var cash: Boolean = false,

    @ColumnInfo(name = "sale_date")
    var date: String = "1/4/2021",

    @ColumnInfo(name = "sale_customer")
    var name: String = "",

    @ColumnInfo(name = "sale_transport")
    var transport: Float = 0.0F,

    @ColumnInfo(name = "sale_other_charges")
    var otherCharges: Float = 0.0F,

    @ColumnInfo(name = "sale_amount")
    var amount: Int = 0
)