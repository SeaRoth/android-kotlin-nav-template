package com.searoth.template.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.searoth.template.R
import com.searoth.template.activity.MealDetailActivity
import com.searoth.template.other.Models
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_food.view.*

/**
 * Created by SeaRoth_2 on 2/14/2018.
 */
class LocalFoodAdapter(val localFeed: Models.LocalFeed): RecyclerView.Adapter<CustomViewHolder>(){

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val meal = localFeed.meals.get(position)
        holder?.view?.tv_detail_name?.text = meal.name

        holder?.view?.tv_address?.text = meal.business.address
        holder?.view?.tv_price?.text = "$"+meal.price.toString()
        holder?.view?.ratingBar?.rating = meal.rating

        val ivFood = holder?.view?.iv_meal
        Picasso.with(holder?.view?.context).load(meal.imageUrl).into(ivFood)

        holder?.foodItem = meal
    }

    override fun getItemCount(): Int {
        return localFeed.meals.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        var layoutInflater = LayoutInflater.from(parent?.context)
        var cellForRow = layoutInflater.inflate(R.layout.row_food, parent, false)
        return CustomViewHolder(cellForRow)
    }
}

class CustomViewHolder(val view: View, var foodItem: Models.FoodItem? = null): RecyclerView.ViewHolder(view) {

    companion object {
        val MEAL_ID_KEY = "MEAL_ID_KEY"
        val MEAL_NAME_KEY = "MEAL_NAME_KEY"
        val MEAL_IMAGE_KEY = "MEAL_IMAGE_KEY"
        val MEAL_RATING_KEY = "MEAL_RATING_KEY"
        val MEAL_PRICE_KEY = "MEAL_PRICE_KEY"
        val MEAL_DESCRIPTION_KEY = "MEAL_DESCRIPTION_KEY"
        val MEAL_NOTES_KEY = "MEAL_NOTES_KEY"
        val MEAL_INGREDIENTS_KEY = "MEAL_INGREDIENTS_KEY"
    }

    init {
        view.setOnClickListener{
            val intent = Intent(view.context, MealDetailActivity::class.java)

            intent.putExtra(MEAL_ID_KEY, foodItem?.id)
            intent.putExtra(MEAL_NAME_KEY, foodItem?.name)
            intent.putExtra(MEAL_IMAGE_KEY, foodItem?.imageUrl)
            intent.putExtra(MEAL_RATING_KEY, foodItem?.rating)
            intent.putExtra(MEAL_PRICE_KEY, foodItem?.price)
            intent.putExtra(MEAL_DESCRIPTION_KEY, foodItem?.description)
            intent.putExtra(MEAL_NOTES_KEY, foodItem?.notes)
            intent.putExtra(MEAL_INGREDIENTS_KEY, foodItem?.ingredients)

            view.context.startActivity(intent)

        }
    }

}