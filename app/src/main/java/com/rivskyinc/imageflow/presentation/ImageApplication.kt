package com.rivskyinc.imageflow.presentation

import android.app.Application
import com.rivskyinc.imageflow.di.DaggerApplicationComponent

class ImageApplication : Application() {

    val component by lazy {
    DaggerApplicationComponent.factory().create(this)
    }
}