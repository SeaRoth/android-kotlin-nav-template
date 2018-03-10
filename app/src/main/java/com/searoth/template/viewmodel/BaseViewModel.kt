package com.searoth.template.viewmodel

import android.app.Activity
import android.app.Fragment
import android.databinding.BaseObservable

/**
 * Created by yusuf on 09/03/2018.
 */
open class BaseViewModel : BaseObservable {

    protected lateinit var activity: Activity

    protected lateinit var fragment: Fragment

    constructor(fragment: Fragment) {
        this.fragment = fragment
    }

    constructor(activity: Activity) {
        this.activity = activity
    }

    protected fun <T> notifyData(param: String?, func: (param: String?) -> T): T {
        notifyChange()
        return func(param)
    }
}
