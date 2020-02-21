package com.pixabay.utils.views

import android.content.Context
import android.content.res.TypedArray

import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.pixabay.utils.R
import kotlinx.android.synthetic.main.views_count_layout.view.*
import java.math.RoundingMode

class ViewCountsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleRes) {

    private var typedArray: TypedArray? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.views_count_layout, this, true)

        attrs?.let {
            typedArray = context.obtainStyledAttributes(it, R.styleable.ViewCountsView, 0, 0)

            setIcon(typedArray?.getInt(R.styleable.ViewCountsView_type, 0))
            setCount(typedArray?.getInt(R.styleable.ViewCountsView_count, 0))
        }
    }

    private fun setIcon(iconType: Int?) {
        if (iconType == 0)
            icon.setImageResource(R.drawable.ic_eye)
        else
            icon.setImageResource(R.drawable.ic_heart)
    }

    fun setCount(number: Int?) {
        var value = number.toString()
        if (number != null && number >= 1000)
            value = "${(number.toDouble() / 1000).toBigDecimal().setScale(1, RoundingMode.UP)}k"

        count.text = value
    }
}