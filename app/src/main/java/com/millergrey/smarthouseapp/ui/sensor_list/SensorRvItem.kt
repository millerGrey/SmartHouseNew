package com.millergrey.smarthouseapp.ui.sensor_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.millergrey.smarthouseapp.data.poko.sensor.Sensor

class SensorRvItem {
    @Composable
    fun setupView(sensor: Sensor) {
//        Row(modifier = Modifier.fillMaxSize()) {
            Text(sensor.value.toString())
//        }
    }
}