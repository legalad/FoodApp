package com.example.foodapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.ui.components.SearchTextField

@Composable
fun BrowseRecipesScreen() {
    Column (modifier = Modifier.fillMaxSize()){
        SearchTextField(
            onStartValue = "test",
            onValueChange = {},
            label = { Text(text = "Search recipes") },
            modifier = Modifier.fillMaxWidth())
        RecipesList()
    }
}

@Composable
fun RecipesList() {
    LazyColumn() {
        items(4) {
            RecipesListItem()
        }
    }
}

@Composable
fun RecipesListItem() {
    ElevatedCard (modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                painter = painterResource(id = R.drawable.salatka_cezar_01_1),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth())
            Text(text = "Sałatka cezar", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_star_24),
                    contentDescription = "",
                    tint = Color(0xFFffe500)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_star_24),
                    contentDescription = "",
                    tint = Color(0xFFffe500)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_star_24),
                    contentDescription = "",
                    tint = Color(0xFFffe500)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_star_24),
                    contentDescription = "",
                    tint = Color(0xFFffe500)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_round_star_24),
                    contentDescription = "",
                    tint = Color(0xFFffe500)
                )
            }
            Text(text = "Author")
            Divider()
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "type")
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "ingredients")
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "")
                }
            }
            Divider()
            Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_timer_24),
                        contentDescription = "time"
                    )
                    Text(text = "45 min")
                }
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.icons8_tableware_48),
                        contentDescription = "how many persons",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = " 1-4 os.")
                }
                Icon(painter = painterResource(id = R.drawable.speedometer_easy), contentDescription = "easy to do", tint = Color.Unspecified, modifier = Modifier.size(24.dp))
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun BrowseRecipesScreenPrev() {
    BrowseRecipesScreen()
}

@Preview (showBackground = true)
@Composable
fun Test() {
    RecipesListItem()
}