package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.data.Datasource
import com.example.foodapp.model.Ingredient
import com.example.foodapp.ui.FoodAppScreen
import com.example.foodapp.ui.theme.FoodAppTheme
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ingredientList = mutableListOf<Ingredient>()
        try {
            val inputStream = resources.openRawResource(R.raw.generic_food)
            val bufferedReader = BufferedReader(inputStream.bufferedReader())
            val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
            for (csvRecord in csvParser) {
                ingredientList.add(Ingredient(name = csvRecord.get(0).toString(), scientificName = csvRecord.get(1).toString(), group = csvRecord.get(2).toString(), subGroup = csvRecord.get(3).toString()))
            }
            bufferedReader.close()
            inputStream.close()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        Datasource.ingredientList.addAll(ingredientList)
        setContent {
            FoodAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FoodAppScreen()
                }
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
    FoodAppTheme {
        Greeting("Android")
    }
}