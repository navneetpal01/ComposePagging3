package com.example.composepagging3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [BeerEntity::class],
    version = 1
)
abstract class BeerDatabase : RoomDatabase() {

    abstract val beerDao: BeerDao

}
