package com.example.angler_diary.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.angler_diary.logic.form.score.EntityWithFScore
import com.example.angler_diary.logic.form.score.FScoreVisitor
import java.util.Date

@Entity(
    tableName = "fishing_trip",
    foreignKeys = [ForeignKey(
        entity = FishingGround::class,
        parentColumns = ["id"],
        childColumns = ["fishingGroundId"],
        onDelete = ForeignKey.RESTRICT
    )]
)
data class FishingTrip(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val fishingGroundId: Int,
    val startDate: Date = Date(),
    val endDate: Date? = null,
    var score: Float = 0f // Automatically calculated
): FishingObjectEntity, EntityWithFScore {
    val durationHours: Float
        get() = endDate?.let {
            val diffInMillis = it.time - startDate.time
            diffInMillis / (1000 * 60 * 60).toFloat() // Convert to hours
        } ?: 0.0f

    override suspend fun accept(visitor: FScoreVisitor): Float {
        return visitor.visitFishingTrip(this)
    }
}
