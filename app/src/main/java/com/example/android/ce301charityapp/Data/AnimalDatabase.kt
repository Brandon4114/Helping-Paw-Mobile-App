package com.example.android.ce301charityapp.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Animal::class,AnimalProgressPoints::class], version =4, exportSchema = false)
abstract class AnimalDatabase: RoomDatabase() {

    abstract fun animalDao(): AnimalDao
    abstract fun animalProgressPoints(): AnimalProgressPointsDao

    companion object{
        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getDatabase(context: Context): AnimalDatabase{
            if(INSTANCE == null){
             synchronized(this){
                 INSTANCE = Room.databaseBuilder(
                     context.applicationContext,
                     AnimalDatabase::class.java,
                     "animals.db"
                 ).fallbackToDestructiveMigration().build()
             }
            }
            return INSTANCE!!
        }
    }
}