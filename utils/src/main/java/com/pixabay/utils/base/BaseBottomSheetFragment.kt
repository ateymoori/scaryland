package com.pixabay.utils.base

import android.os.Handler
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    override fun dismiss() {
        Handler().postDelayed({
            try {
                super.dismiss()
            } catch (e: Exception) {
                //dialog not show
            }
        }, 400)
    }
}