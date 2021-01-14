package com.mohammedsendi.senators

import android.app.Application

class SenatesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SenatorsRepository.initialize(this)
    }

}