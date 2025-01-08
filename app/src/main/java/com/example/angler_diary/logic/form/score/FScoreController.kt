package com.example.angler_diary.logic.form.score

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish

class FScoreController(private val viewModel: DatabaseViewModel) {
    private val calculator = FScoreCalculator(viewModel)

    suspend fun handleScoreOnDelete(){
        reCalcAllScore()
    }

    suspend fun handleScoreOnEntity(entity: Fish){
        updateScore(entity)
        reCalcAllScore()
    }

    private suspend fun updateScore(entity: Fish){
        val score = calculator.visitFish(entity)
        entity.score = score
        viewModel.update(entity)
    }

    private suspend fun reCalcAllScore(){}
}