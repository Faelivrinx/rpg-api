package com.mypieceofcode.rpgapi.application.items.dto

import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.items.Item
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException

data class ItemDto(
        val name: String,
        val description: String,
        val availability: String,
        val price: Float,
        val id: String
) {

    companion object {
        fun fromItem(item: Item) : ItemDto {
            return if (item.id != null){
                ItemDto(item.name, item.description, item.availability.toString(), item.price, item.id)
            } else {
                throw InvalidEntityException(ErrorCode.ITEM_EMPTY_ID)
            }
        }


    }
}
