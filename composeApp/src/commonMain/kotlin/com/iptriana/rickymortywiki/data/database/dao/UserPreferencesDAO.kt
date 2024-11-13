package com.iptriana.rickymortywiki.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.iptriana.rickymortywiki.data.database.entity.CharacterOfTheDayEntity

@Dao
interface UserPreferencesDAO {
    @Query("SELECT * FROM character_of_the_day")
    suspend fun getCharacterOfTheDay(): CharacterOfTheDayEntity?
}