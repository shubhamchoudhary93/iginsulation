package com.shubham.iginsulation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransaction
import com.shubham.iginsulation.database.shopstock.ShopStock


@BindingAdapter("nameString")
fun TextView.setNameString(item: ShopStockTransaction) {
    item.let {
        text = item.stock
    }

}

@BindingAdapter("quantityString")
fun TextView.setQuantityString(item: ShopStockTransaction) {
    item.let {
        text = item.quantity.toString()

    }
}
