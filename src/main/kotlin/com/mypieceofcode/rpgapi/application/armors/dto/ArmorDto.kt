package com.mypieceofcode.rpgapi.application.armors.dto

import com.mypieceofcode.rpgapi.domain.custom.Price
import com.mypieceofcode.rpgapi.domain.equipment.armors.Armor
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException

data class ArmorDto(
        val name: String,
        val type: String,
        val protectionAreas: List<String>,
        val pz: Int,
        val price: Price,
        val weight: Float,
        val availability: String,
        val description: String,
        val url: String,
        val id: String
) {
    companion object {
        fun fromArmor(armor: Armor): ArmorDto {
            return if (armor.id != null) {
                ArmorDto(
                        armor.name,
                        armor.type.toString(),
                        armor.protectionArea.map { it.toString() },
                        armor.pz,
                        armor.price,
                        armor.weight,
                        armor.availability.toString(),
                        armor.description,
                        armor.url,
                        armor.id
                )
            } else {
                throw InvalidEntityException(ErrorCode.ARMOR_EMPTY_ID)
            }
        }
    }
}