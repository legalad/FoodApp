package com.example.foodapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodAppTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Logo()
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = "", Modifier.rotate(180f))
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Composable
fun Logo() {
    Row (verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.icons8_chef_hat_96),
            contentDescription = "logo",
            tint = Color.Unspecified
        )
        Text(
            text = "FoodApp",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun FoodTopAppBarPreview() {
    FoodAppTopAppBar()
}