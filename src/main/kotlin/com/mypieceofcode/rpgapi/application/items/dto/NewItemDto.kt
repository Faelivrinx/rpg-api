package com.mypieceofcode.rpgapi.application.items.dto

import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.enums.Availability.Companion.createAvailability
import com.mypieceofcode.rpgapi.domain.equipment.items.Item

data class NewItemDto(
        val name: String,
        val description: String,
        val availability: String = Availability.HIGH.toString(),
        val price: Float = 0f
) {

    companion object {
        fun toItem(dto: NewItemDto) = Item(dto.name, dto.description, createAvailability(dto.availability), dto.price)
    }
}