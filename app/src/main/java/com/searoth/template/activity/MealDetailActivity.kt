package com.searoth.template.activity

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import com.searoth.template.adapter.CustomViewHolder
import com.searoth.template.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_meal_detail.*

class MealDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)
        val navBarTitle = intent.getStringExtra(CustomViewHolder.MEAL_NAME_KEY)

        toolbar.setBackgroundColor(Color.TRANSPARENT)
        setSupportActionBar(toolbar)
        supportActionBar?.title = navBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        setUI()
    }

    fun setUI(){
        val name = intent.getStringExtra(CustomViewHolder.MEAL_NAME_KEY)
        tv_detail_name?.text = name
        println(name)
        rb_detail_rating?.rating = intent.getFloatExtra(CustomViewHolder.MEAL_RATING_KEY,0.0f)
        val price = "$"+intent.getDoubleExtra(CustomViewHolder.MEAL_PRICE_KEY,0.0)
        tv_detail_price?.text = price
        tv_detail_description?.text = intent.getStringExtra(CustomViewHolder.MEAL_DESCRIPTION_KEY)
        tv_detail_notes?.text = intent.getStringExtra(CustomViewHolder.MEAL_NOTES_KEY)

        val ingredientsArray = intent.getStringArrayExtra(CustomViewHolder.MEAL_INGREDIENTS_KEY)
        val s = TextUtils.join(",", ingredientsArray)
        tv_detail_ingredients?.text = s
        val img = ll_details_image
        val url = intent.getStringExtra(CustomViewHolder.MEAL_IMAGE_KEY)
        Picasso.with(this).load(url).into(img)
    }


}

