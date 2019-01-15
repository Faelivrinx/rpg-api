package com.mypieceofcode.rpgapi.application.items.dto

import com.mypieceofcode.rpgapi.domain.custom.Price
import com.mypieceofcode.rpgapi.domain.equipment.items.Item
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException

data class ItemDto(
        val name: String,
        val description: String,
        val availability: String,
        val price: Price,
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
