package com.millergrey.smarthouseapp.data.repository

import android.util.Log
import com.millergrey.smarthouseapp.data.network.sensor.SensorApi
import com.millergrey.smarthouseapp.data.poko.sensor.Sensor
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip

class SensorRepository(
    private val sensorApi: SensorApi = SensorApi()
) {
    suspend fun getSensors(): Flow<List<Sensor>> {
        val sensorList = mutableListOf<Sensor>()
        try {
        val body: String = sensorApi.getSensors().body()
        for (line in body.split("\r\n")) {
            if (line.isNotEmpty()) {
                sensorList.add(parceSensor(line))
            }
        }
        val b = sensorApi.getSensorsValues().body<String>().split('/')
        sensorList.forEachIndexed { index, sensor ->
            sensor.value = b[index].toFloat()
        }
//            return sensorList
            return flow { emit(sensorList) }
                .zip(flow {
                    emit(
                        sensorApi.getSensorsValues().body<String>().split('/')
                    )
                }) { sensors, values ->
                    sensors.forEachIndexed { index, sensor ->
                        sensor.value = values[index].toFloat()
                    }
                    sensors
                }
        } catch(e: java.lang.Exception) {
            Log.d("KTOR", "repo")
            return (flow{listOf<Sensor>()})
        }
    }

    private fun parceSensor(str: String): Sensor {
        val array = str.split(",")
        return Sensor(
            num = array[0].toInt(),
            id = array[1],
            location = array[2],
            description = array[3],
            model = array[4],
            type = array[5]
        )
    }
}