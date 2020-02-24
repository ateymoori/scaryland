package com.pixabay.utils.tools

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.round


fun String.log(tag: String? = null) {
    if (tag != null)
        Log.d(tag, this)
    else
        Log.d("debug_", this)
}

infix fun Double.round(decimals: Int): Double {
    var multiplier = 1.00
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}


fun String.toast(ctx: Context?) {
    Toast.makeText(ctx, this, Toast.LENGTH_LONG).show()
}

fun <T : androidx.recyclerview.widget.RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}


fun Long.length() = when (this) {
    0.toLong() -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

