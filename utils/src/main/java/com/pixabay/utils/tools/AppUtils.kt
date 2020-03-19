package com.pixabay.utils.tools

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import java.io.IOException
import java.io.InputStream


class AppUtils {


    companion object {

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


        fun assetsToString(context: Context?, file: String): String? {
            var json: String? = null
            try {
                val inputStream: InputStream? = context?.assets?.open(file)
                if (inputStream != null) {

                    val size: Int = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    inputStream.close()
                    json = String(buffer, Charsets.UTF_8)
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
            return json
        }

        fun openGooglePlay(context:Context?){
            val uri: Uri = Uri.parse("market://details?id=" + context?.packageName)
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
            try {
                context?.startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                context?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + context?.getPackageName())
                    )
                )
            }
        }

    }
}