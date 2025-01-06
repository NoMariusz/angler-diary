package com.example.angler_diary.ui.form.manager.common

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.EMPTY_ID
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.ui.form.inputs.InputsController

class FishingGroundFormManagerCommon(private val context: Context): FormManagerCommon<FishingGround> {
    private lateinit var inputsController: InputsController

    fun initialiseInputs(view: ViewGroup, default: FishingGround?) {
        inputsController = InputsController(view, context)

        inputsController.create("name", "Name: ", default?.name)
    }

    override fun getObjectWithActualState(id: Int): FishingGround {
        return FishingGround(
            id,
            inputsController.getString("name")
                ?: throw Exception("Cannot get value for FishingGround 'name'")
        )
    }
}