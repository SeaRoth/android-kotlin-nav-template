package com.searoth.template.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.searoth.template.R


/**
 * Created by SeaRoth_2 on 9/8/2017.
 */

class Toast(var currentToast: Toast? = null) {

    fun showShort(context: Context, message: String) {
        if (currentToast != null) currentToast!!.cancel()
        val inflater = (context as Activity).layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, context.findViewById<View>(R.id.root) as ViewGroup)
        val text = layout.findViewById<View>(R.id.message) as TextView
        val toast = Toast(context)

        text.text = message

        toast.duration = Toast.LENGTH_SHORT

        toast.view = layout
        toast.show()
        currentToast = toast
    }

    fun showLong(context: Context, message: String) {
        if (currentToast != null) currentToast!!.cancel()
        val inflater = (context as Activity).layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, context.findViewById<View>(R.id.root) as ViewGroup)
        val text = layout.findViewById<View>(R.id.message) as TextView

        text.text = message

        val toast = Toast(context)

        toast.duration = Toast.LENGTH_LONG

        toast.view = layout
        toast.show()
        currentToast = toast
    }
}
