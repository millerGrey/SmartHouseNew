package com.millergrey.smarthouseapp.data.network.sensor

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class SensorApi {
     private val httpClient = HttpClient(Android) {
        defaultRequest {
            url ("http://192.168.0.202/")
            header("connection", "keep-alive")
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }
            }
            level = LogLevel.ALL
        }
    }
    suspend fun getSensors(): HttpResponse {
        return httpClient.get("data/dsList.csv")

    }
    suspend fun getSensorsValues(): HttpResponse {
        return httpClient.get("cgi-bin/sensor/value/").call.response
    }
}