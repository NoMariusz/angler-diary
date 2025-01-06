package com.example.angler_diary.ui.form.manager.common

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.EMPTY_ID
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.ui.form.inputs.InputsController
import com.example.angler_diary.ui.form.inputs.RelationOption
import java.util.Date

class FishingTripFormManagerCommon(private val context: Context) {
    private lateinit var inputsController: InputsController

    fun initialiseInputs(
        view: ViewGroup,
        default: FishingTrip?,
        availableGrounds: List<FishingGround>
    ) {
        inputsController = InputsController(view, context)

        inputsController.create(
            "ground",
            "Fishing ground: ",
            default?.fishingGroundId,
            createOptions(availableGrounds)
        )
        inputsController.create("start", "Start date: ", default?.startDate)
        inputsController.create("end", "End date: ", default?.endDate)
    }

    fun getObjectWithActualState(): FishingTrip {
        return FishingTrip(
            0,
            inputsController.getInt("ground")
                ?: EMPTY_ID,
            inputsController.getDate("start") ?: Date(),
            inputsController.getDate("end")
        )
    }

    private fun createOptions(availableGrounds: List<FishingGround>): List<RelationOption> {
        return availableGrounds.map { ground ->
            RelationOption(
                id = ground.id,
                text = ground.name
            )
        }
    }
}