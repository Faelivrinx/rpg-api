package com.mypieceofcode.rpgapi.application.items.dto

import com.mypieceofcode.rpgapi.domain.custom.Price
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.equipment.items.Item

data class NewItemDto(
        val name: String,
        val description: String,
        val availability: String = "HIGH",
        val price: Price = Price(0f, 0f, 0f)
) {

    companion object {
        fun toItem(dto: NewItemDto) = Item(dto.name, dto.description, Availability.createAvailability(dto.availability), dto.price)
    }
}