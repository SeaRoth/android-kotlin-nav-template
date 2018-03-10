package com.searoth.template.viewmodel.main.activity

import android.app.Activity
import com.google.gson.GsonBuilder
import com.searoth.template.model.LocalFeed
import com.searoth.template.viewmodel.BaseViewModel
import okhttp3.*
import java.io.IOException

/**
 * Created by yusuf on 09/03/2018.
 */
class MainActivityViewModel(activity: Activity, val mListener: OnSuccess?) : BaseViewModel(activity) {

    init {
        fetchJSON()
    }

    private fun fetchJSON() {
        println("Attempting to Fetch JSON")

        val url = "http://www.gamermessenger.com/savorlyapp/food_feed.json"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                //println(body)
                val gson = GsonBuilder().create()
                val listOfFoodForUser = gson.fromJson(body, LocalFeed::class.java)

                mListener?.onSuccessLoad(listOfFoodForUser)

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }
}

interface OnSuccess {
    fun onSuccessLoad(list: LocalFeed)
}