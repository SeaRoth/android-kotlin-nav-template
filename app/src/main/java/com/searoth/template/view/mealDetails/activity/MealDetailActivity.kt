package com.searoth.template.view.mealDetails.activity

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.WindowManager
import com.searoth.template.R
import com.searoth.template.adapter.CustomViewHolder
import com.searoth.template.databinding.ActivityMealDetailBinding
import com.searoth.template.viewmodel.mealDetails.activity.MealDetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_meal_detail.*

class MealDetailActivity : AppCompatActivity() {

    var binding: ActivityMealDetailBinding? = null
    var viewModel: MealDetailsActivityViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navBarTitle = intent.getStringExtra(CustomViewHolder.MEAL_NAME_KEY)
        val price = intent.getDoubleExtra(CustomViewHolder.MEAL_PRICE_KEY, 0.0)
        val rating = intent.getFloatExtra(CustomViewHolder.MEAL_RATING_KEY, 0.0f)
        val description = intent.getStringExtra(CustomViewHolder.MEAL_DESCRIPTION_KEY)
        val name = intent.getStringExtra(CustomViewHolder.MEAL_NAME_KEY)
        val imageUrl = intent.getStringExtra(CustomViewHolder.MEAL_IMAGE_KEY)
        val detailsNote = intent.getStringExtra(CustomViewHolder.MEAL_NOTES_KEY)

        val ingredientsArray = intent.getStringArrayExtra(CustomViewHolder.MEAL_INGREDIENTS_KEY)
        val s = TextUtils.join(",", ingredientsArray)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_meal_detail)
        viewModel = MealDetailsActivityViewModel(this,
                price,
                rating,
                description,
                name,
                imageUrl,
                detailsNote,
                s)
        binding?.viewmodel = viewModel


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
    }

}

