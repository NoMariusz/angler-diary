package com.example.angler_diary.ui.form.manager.common

import com.example.angler_diary.EMPTY_ID
import com.example.angler_diary.database.entities.FishingObjectEntity

interface FormManagerCommon<T: FishingObjectEntity> {
    fun getObjectWithActualState(id: Int = EMPTY_ID): T
}