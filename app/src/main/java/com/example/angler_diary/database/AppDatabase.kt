package com.example.angler_diary.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.angler_diary.database.converters.DateConverter
import com.example.angler_diary.database.entities.*
import com.example.angler_diary.database.dao.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [FishingGround::class, FishingTrip::class, FishSpecies::class, Fish::class, ScoreHistory::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fishingGroundDao(): FishingGroundDao
    abstract fun fishingTripDao(): FishingTripDao
    abstract fun fishSpeciesDao(): FishSpeciesDao
    abstract fun fishDao(): FishDao
    abstract fun scoreHistoryDao(): ScoreHistoryDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "anglerdiary_database"
                )
                    .addCallback(DatabaseCallback(context, scope))
                    .build().also { instance = it }
            }
        }
    }

    private class DatabaseCallback(
        private val context: Context,
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Populate default data on a background thread
            scope.launch {
                populateDatabase(getInstance(context, scope))
            }
        }

        private suspend fun populateDatabase(db: AppDatabase) {
            db.fishSpeciesDao().insertAll(loadFishSpeciesFromJson(context))
            createStartScoreHistory(db)
        }

        private fun loadFishSpeciesFromJson(context: Context): List<FishSpecies> {
            val jsonString =
                context.assets.open("initial_fish_species.json").bufferedReader().use { it.readText() }
            val gson = Gson()
            val type = object : TypeToken<List<FishSpecies>>() {}.type
            return gson.fromJson(jsonString, type)
        }

        private suspend fun createStartScoreHistory(db: AppDatabase) {
            db.scoreHistoryDao().insert(ScoreHistory(score = 0f))
        }
    }
}
