package com.eliorcohen.projecttingzkotlin.UtilsPackage

import android.app.Application

class ApplicationUtil : Application() {

    override fun onCreate() {
        super.onCreate()

        application = this
    }

    companion object {
        var application: Application? = null
    }

}
