package com.mypieceofcode.rpgapi.application.items.dto

import com.mypieceofcode.rpgapi.domain.equipment.items.Item

data class NewItemDto(
        val name: String,
        val description: String,
        val availability: String = "HIGH",
        val price: Float = 0f
) {

    companion object {
        fun toItem(dto: NewItemDto) = Item(dto.name, dto.description, dto.availability, dto.price)
    }
}