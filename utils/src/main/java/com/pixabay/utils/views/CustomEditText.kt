package com.pixabay.utils.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Handler
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.pixabay.utils.R
import com.pixabay.utils.tools.afterTextChanged
import kotlinx.android.synthetic.main.edittext_a.view.*


class CustomEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleRes) {

    private lateinit var finishingConstraintSet: ConstraintSet
    private lateinit var startingConstraintSet: ConstraintSet
    var set = false
    private var typedArray: TypedArray? = null


    init {
        LayoutInflater.from(context).inflate(R.layout.edittext_a, this, true)

        initView()
//
//        attrs?.let {
//            typedArray = context.obtainStyledAttributes(it, R.styleable.ViewCountsView, 0, 0)
//
//            setIcon(typedArray?.getInt(R.styleable.ViewCountsView_type, 0))
//            setCount(typedArray?.getInt(R.styleable.ViewCountsView_count, 0))
//        }
    }


    private fun initView() {
        startingConstraintSet = ConstraintSet()
        startingConstraintSet.clone(container)
        finishingConstraintSet = ConstraintSet()
        finishingConstraintSet.clone(context, R.layout.edittext_b)
        editText.afterTextChanged {
            addAnimationOperations(container, it.isNotEmpty())
        }
    }

    private fun addAnimationOperations(root: ConstraintLayout, isTyping: Boolean) {
        val autoTransition = AutoTransition()
        autoTransition.duration = 500
        TransitionManager.beginDelayedTransition(root, autoTransition)
        val constraint = if (isTyping) finishingConstraintSet else startingConstraintSet
        constraint.applyTo(root)
        if (constraint == finishingConstraintSet) {
            Handler().postDelayed({
                placeHolder.setBackgroundColor(getBackgroundColor())
            }, 400)

        } else {
            Handler().postDelayed({
                placeHolder.setBackgroundColor(Color.TRANSPARENT)
            }, 200)
        }
    }

    private fun getBackgroundColor(): Int {
        val a = TypedValue()
        context.theme.resolveAttribute(android.R.attr.windowBackground, a, true)
        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            return a.data
        }
        return 0

    }

}