package com.jihan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.jihan.app.datastore.DatastoreUtil
import com.jihan.app.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()





        setContent {
            AppTheme {
               MainApp()
            }
        }
    }

}

@Composable
fun MainApp(modifier: Modifier = Modifier) {

    val datastore = koinInject<DatastoreUtil>()


    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

 var key by rememberSaveable { mutableStateOf("") }
 var value by rememberSaveable { mutableStateOf("") }
    var data by rememberSaveable { mutableStateOf("") }


        Text(data, fontSize = 35.sp)

    TextField(
        value = key,
        onValueChange = { key = it },
        label = { Text("Key") }
    )

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Value") }
    )


        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
            datastore.saveData(key,value)
            }
        }) { Text("Save") }
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                 data = datastore.readData(key)?:"null"
            }
        }) { Text("Retrieve with readData") }

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                data = datastore.getData(key)?:"null"
            }
        }) { Text("Retrieve with getData") }


    }




}



