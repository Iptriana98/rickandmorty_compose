package com.iptriana.rickymortywiki.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.iptriana.rickymortywiki.data.database.dao.UserPreferencesDAO
import com.iptriana.rickymortywiki.data.database.entity.CharacterOfTheDayEntity

const val DATABASE_NAME = "rickymorty.db"

expect object RickyMortyCtor: RoomDatabaseConstructor<RickyMortyDatabase>

@Database(entities = [CharacterOfTheDayEntity::class], version = 1)
@ConstructedBy(RickyMortyCtor::class)
abstract class RickyMortyDatabase : RoomDatabase(){
    //DAOs
    abstract fun getPreferencesDao(): UserPreferencesDAO
}