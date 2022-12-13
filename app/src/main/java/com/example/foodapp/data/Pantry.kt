package com.example.foodapp.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "pantry_table",
    foreignKeys = [ForeignKey(entity = Ingredient::class, parentColumns = ["id"], childColumns = ["ingredient_id"], onDelete = ForeignKey.NO_ACTION)]
)
data class Pantry (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val bar_code: String? = null,
    val place_date: Date,
    val expire_date: Date,
    val quantity: Float,
    val unit: String,
    val ingredient_id: Int
)