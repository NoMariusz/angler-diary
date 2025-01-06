package com.example.angler_diary.ui.form.manager.common

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.EMPTY_ID
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.database.entities.FishingTripSummary
import com.example.angler_diary.ui.form.inputs.InputsController
import com.example.angler_diary.ui.form.inputs.RelationOption
import java.util.Date

class FishFormManagerCommon(private val context: Context) : FormManagerCommon<Fish> {
    private lateinit var inputsController: InputsController

    fun initialiseInputs(
        view: ViewGroup,
        default: Fish?,
        species: List<FishSpecies>,
        trips: List<FishingTripSummary>
    ) {
        inputsController = InputsController(view, context)

        inputsController.create(
            "species",
            "Species: ",
            default?.speciesId,
            createOptions(species)
        )
        inputsController.create("weight", "Weight(g): ", default?.weight)
        inputsController.create("length", "Length(cm): ", default?.length)
        inputsController.create("catchDate", "Catch date: ", default?.catchDate)
        inputsController.create(
            "trip",
            "Fishing trip: ",
            default?.fishingTripId,
            createOptions(trips)
        )
    }

    override fun getObjectWithActualState(id: Int): Fish {
        return Fish(
            id,
            inputsController.getInt("species")
                ?: EMPTY_ID,
            null,
            inputsController.getFloat("weight"),
            inputsController.getFloat("length"),
            inputsController.getDate("catchDate") ?: Date(),
            inputsController.getInt("trip")
        )
    }

    private fun createOptions(species: List<FishSpecies>): List<RelationOption> {
        return species.map { singleSpecies ->
            RelationOption(
                id = singleSpecies.id,
                text = singleSpecies.name
            )
        }
    }

    @JvmName("createOptionsTrips")
    private fun createOptions(trips: List<FishingTripSummary>): List<RelationOption> {
        return trips.map { trip ->
            RelationOption(
                id = trip.id,
                text = trip.toString()
            )
        }
    }
}