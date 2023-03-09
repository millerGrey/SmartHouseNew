package com.millergrey.smarthouseapp.data.poko.sensor

import android.os.Parcelable

class Sensor (
    val id: String = "",
    val num: Int = 0,
    var location: String = "",
    var value: Float = 0F,
    val model: String = "",
    val type: String = "",
    var description: String = ""
)