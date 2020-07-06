package com.example.exchangeapp.io.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [(ItemModel::class)],
    version = 1,
    exportSchema = true
)
abstract class DataBase : RoomDatabase() {
    abstract val itemDao: ItemModelDao
}
