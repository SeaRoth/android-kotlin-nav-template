package com.searoth.template.viewmodel.mealDetails.activity

import android.app.Activity
import android.databinding.ObservableField
import com.searoth.template.util.convertToCurrency
import com.searoth.template.viewmodel.BaseViewModel

/**
 * Created by yusuf on 10/03/2018.
 */
class MealDetailsActivityViewModel(activity: Activity, price: Double, rating: Float, decription: String, details: String, imageUrl: String, detailsNote: String, ingredient: String) : BaseViewModel(activity) {

    var priceObservable: ObservableField<String> = ObservableField("")
    var descriptionObservable: ObservableField<String> = ObservableField("")
    var detailsNameObservable: ObservableField<String> = ObservableField("")
    var detailsNoteObservable: ObservableField<String> = ObservableField("")
    var imageUrlObservable: ObservableField<String> = ObservableField("")
    var ingredientObservable: ObservableField<String> = ObservableField("")
    var ratingObservable: ObservableField<Float> = ObservableField(0F)

    init {
        priceObservable.set(convertToCurrency("$", price))
        ratingObservable.set(rating)
        descriptionObservable.set(decription)
        detailsNameObservable.set(details)
        imageUrlObservable.set(imageUrl)
        ingredientObservable.set(ingredient)
        detailsNoteObservable.set(detailsNote)
    }

}