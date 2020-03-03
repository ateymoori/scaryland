package com.pixabay.utils.tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

class AppUtils {


    companion object{

        fun shareToMessagingApps(mActivity: Activity?, title: String, message: String) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            mActivity?.startActivity(Intent.createChooser(intent, title))
        }


        fun appVersion(ctx: Context): String {
            return try {
                val pInfo = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
                val version = pInfo.versionName
                version;
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                "000"
            }
        }
    }
}