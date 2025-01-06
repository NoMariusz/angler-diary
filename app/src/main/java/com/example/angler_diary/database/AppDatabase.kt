package com.example.angler_diary.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import androidx.room.AutoMigration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.angler_diary.database.converters.DateConverter
import com.example.angler_diary.database.entities.*
import com.example.angler_diary.database.dao.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [FishingGround::class, FishingTrip::class, FishSpecies::class, Fish::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fishingGroundDao(): FishingGroundDao
    abstract fun fishingTripDao(): FishingTripDao
    abstract fun fishSpeciesDao(): FishSpeciesDao
    abstract fun fishDao(): FishDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

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

    private class DatabaseCallback(private val context: Context, private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Populate default data on a background thread
            scope.launch {
                populateDatabase(getInstance(context, scope))
            }
        }

        private suspend fun populateDatabase(db: AppDatabase) {
            db.fishSpeciesDao().insertAll(getDefaultFishSpecies())
        }

        private fun getDefaultFishSpecies(): List<FishSpecies> {
            return listOf(
                FishSpecies(name = "Karp", basePoints = 100, averageWeight = 2000f, averageLength = 45f),
                FishSpecies(name = "Karaś", basePoints = 2, averageWeight = 250f, averageLength = 25f),
                FishSpecies(name = "Leszcz", basePoints = 20, averageWeight = 1000f, averageLength = 30f),
                FishSpecies(name = "Płoć", basePoints = 2, averageWeight = 150f, averageLength = 20f)
            )
        }
    }
}
