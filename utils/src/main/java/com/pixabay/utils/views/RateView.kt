package com.pixabay.utils.views

import android.content.Context
import android.content.res.TypedArray

import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.pixabay.utils.R
import kotlinx.android.synthetic.main.circle_rate_view.view.*
import kotlinx.android.synthetic.main.views_count_layout.view.*
import java.math.RoundingMode

class RateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyleRes) {

    private var typedArray: TypedArray? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.circle_rate_view, this, true)

        attrs?.let {
            typedArray = context.obtainStyledAttributes(it, R.styleable.RateView, 0, 0)

            val rateAmount = typedArray?.getString(R.styleable.RateView_rate) ?: "0.0"
            rate = rateAmount
        }
    }

    var rate: String = "0.0"
        set(value) {
            field = value
            rateView.text = value
        }


}