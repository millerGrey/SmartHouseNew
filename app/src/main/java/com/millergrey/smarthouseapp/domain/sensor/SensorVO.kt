package com.millergrey.smarthouseapp.domain.sensor

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SensorVO(
    val id: String = "",
    val num: Int = 0,
    var location: String = "",
    var value: Float = 0F,
    val model: String = "",
    val type: String = "",
    var description: String = ""
): Parcelable {
}