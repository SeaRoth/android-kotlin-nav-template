package com.searoth.template.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.searoth.template.R


/**
 * Created by Yusuf on 10/3/2018.
 */

class Toast {

    companion object {

        private var currentToast: Toast? = null

        private fun initText(toast: Toast, layout: View, message: String, toastLength: Int) {

            val text = layout.findViewById<View>(R.id.message) as TextView

            toast.duration = toastLength

            text.text = message

            toast.view = layout

            toast.show()

            currentToast = toast
        }

        @SuppressLint("InflateParams")
        fun showShort(context: Context, message: String) {
            if (currentToast != null) currentToast!!.cancel()

            val inflater = (context as Activity).layoutInflater
            val layout = inflater.inflate(R.layout.custom_toast, null)
            val toast = Toast(context)

            initText(toast, layout, message, Toast.LENGTH_SHORT)

            toast.duration = Toast.LENGTH_SHORT

        }

        @SuppressLint("InflateParams")
        fun showLong(context: Context, message: String) {
            if (currentToast != null) currentToast!!.cancel()

            val inflater: LayoutInflater = (context as Activity).layoutInflater

            val layout = inflater.inflate(R.layout.custom_toast, null)

            val toast = Toast(context)

            initText(toast, layout, message, Toast.LENGTH_LONG)

        }
    }
}
