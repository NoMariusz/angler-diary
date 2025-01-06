package com.example.angler_diary.ui.form.manager.common

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.DEFAULT_FISH_SPECIES_AVERAGE_LENGTH
import com.example.angler_diary.DEFAULT_FISH_SPECIES_AVERAGE_WEIGHT
import com.example.angler_diary.DEFAULT_FISH_SPECIES_BASE_POINTS
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.ui.form.inputs.InputsController

class FishSpeciesFormManagerCommon(private val context: Context) : FormManagerCommon<FishSpecies> {
    private lateinit var inputsController: InputsController

    fun initialiseInputs(
        view: ViewGroup,
        default: FishSpecies?
    ) {
        inputsController = InputsController(view, context)

        inputsController.create("name", "Name: ", default?.name)
        inputsController.create("basePoints", "Base points: ", default?.basePoints)
        inputsController.create("averageWeight", "Average weight(g): ", default?.averageWeight)
        inputsController.create("averageLength", "Average length(cm): ", default?.averageLength)
    }

    override fun getObjectWithActualState(id: Int): FishSpecies {
        return FishSpecies(
            id,
            inputsController.getString("name") ?: throw Exception("Cannot get value for FishingGround 'name'"),
            null,
            inputsController.getInt("basePoints") ?: DEFAULT_FISH_SPECIES_BASE_POINTS,
            inputsController.getFloat("averageWeight") ?: DEFAULT_FISH_SPECIES_AVERAGE_WEIGHT,
            inputsController.getFloat("averageLength") ?: DEFAULT_FISH_SPECIES_AVERAGE_LENGTH,
        )
    }
}