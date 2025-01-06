package com.example.angler_diary.logic.form.score

interface EntityWithFScore {
    suspend fun accept(visitor: FScoreVisitor): Float
}