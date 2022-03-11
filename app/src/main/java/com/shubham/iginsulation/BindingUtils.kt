package com.shubham.iginsulation

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransaction


@BindingAdapter("nameString")
fun TextView.setNameString(item: ShopStockTransaction) {
    item.let {
        text = item.stock
    }

}

@BindingAdapter("quantityString")
fun TextView.setQuantityString(item: ShopStockTransaction) {
    item.let {
        if (item.add)
            setTextColor(Color.parseColor("#006600"))
        else {
            setTextColor(Color.parseColor("#FF0000"))
        }
        text = item.quantity.toString()

    }
}

@BindingAdapter("dateString")
fun TextView.setDateString(item: ShopStockTransaction) {
    item.let {
        text = item.date
    }

}
