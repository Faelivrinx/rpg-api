package com.mypieceofcode.rpgapi.application.items.dto

import com.mypieceofcode.rpgapi.domain.equipment.items.Item

data class UpdateItemDto(
        val name: String,
        val description: String,
        val availability: String,
        val price: Float,
        val id: String
) {

    companion object {
        fun toItem(dto: UpdateItemDto) = Item(dto.name, dto.description, dto.availability, dto.price, dto.id)
    }
}