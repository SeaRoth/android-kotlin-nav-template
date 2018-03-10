package com.searoth.template.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by yusuf on 10/03/2018.
 */
class BindingUtil {
    companion object {

        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: ImageView, imageUrl: String) {
            Picasso.with(imageView.context).load(imageUrl).into(imageView)
        }

    }
}