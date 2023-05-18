package com.millergrey.smarthouseapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.millergrey.smarthouseapp.data.network.sensor.SensorApi
import com.millergrey.smarthouseapp.data.poko.sensor.Sensor
import com.millergrey.smarthouseapp.data.repository.SensorRepository
import com.millergrey.smarthouseapp.ui.sensor_list.SensorRvItem
import com.millergrey.smarthouseapp.ui.theme.SmartHouseAppTheme
import io.ktor.client.call.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch

class MainActivity : ComponentActivity() {

    var sensors: List<Sensor> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            while(true) {
                SensorRepository().getSensors().catch{e->Log.d("KTOR", "catch")}.collect {
                    sensors = it
                }
                withContext(Dispatchers.Main) {
                    setContent {
                        SmartHouseAppTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colors.background
                            ) {
                                Column(modifier = Modifier.fillMaxSize()) {
                                    for (sensor in sensors) {
                                        SensorRvItem().setupView(sensor)
                                    }
                                }
                            }
                        }
                    }
                }
                delay(5000)
            }
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SmartHouseAppTheme {
        Greeting("Android")
    }
}