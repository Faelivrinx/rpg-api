package com.mypieceofcode.rpgapi.application.items.dto

import com.mypieceofcode.rpgapi.domain.custom.Price
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.equipment.items.Item

data class UpdateItemDto(
        val name: String,
        val description: String,
        val availability: String,
        val price: Price,
        val id: String
) {

    companion object {
        fun toItem(dto: UpdateItemDto) = Item(dto.name, dto.description, Availability.createAvailability(dto.availability), dto.price, dto.id)
    }
}