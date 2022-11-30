package com.example.foodapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType

@Composable
fun ScheduleScreen() {
    Kalendar(kalendarType = KalendarType.Firey)
}

@Preview
@Composable
fun CalendarPrev() {
    ScheduleScreen()
}