package com.pixabay.utils.tools

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.pixabay.utils.models.UserModel
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


fun String.toast(ctx: Context?): String {
    Toast.makeText(ctx, this, Toast.LENGTH_LONG).show()
    return this
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


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun GoogleSignInAccount.userModel(): UserModel {
    return UserModel(
        name = this.displayName,
        avatar = this.photoUrl.toString(),
        family = this.familyName,
        givenName = this.givenName,
        email = this.email,
        id = this.id
    )
}

