package com.iptriana.rickymortywiki.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_of_the_day")
data class CharacterOfTheDayEntity(
    @PrimaryKey val id: Int,
    val isAlive: Boolean,
    val name: String,
    val image: String,
    val selectedDate: String
)
