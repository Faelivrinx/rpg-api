package com.mypieceofcode.rpgapi.application.armors.dto

import com.mypieceofcode.rpgapi.domain.enums.ArmorType
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.enums.Availability.Companion.createAvailability
import com.mypieceofcode.rpgapi.domain.enums.ProtectionArea.Companion.createArea
import com.mypieceofcode.rpgapi.domain.equipment.armors.Armor

data class NewArmorDto(
        val name: String,
        val type: String,
        val protectionAreas: List<String>,
        val pz: Int,
        val price: Float = 0.0f,
        val weight: Float = 0.0f,
        val availability: String = Availability.HIGH.toString(),
        val description: String = "",
        val url: String = "",
        val id: String? = null

) {

    companion object {
        fun toArmor(dto: NewArmorDto) : Armor = Armor(
                dto.name,
                ArmorType.createArmorType(dto.type),
                dto.protectionAreas.map { createArea(it) },
                dto.pz,
                dto.price,
                dto.weight,
                createAvailability(dto.availability),
                dto.description,
                dto.url,
                dto.id
        )
    }

}