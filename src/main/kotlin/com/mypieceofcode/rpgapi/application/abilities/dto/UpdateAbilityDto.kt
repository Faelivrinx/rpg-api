package com.mypieceofcode.rpgapi.application.abilities.dto

import com.mypieceofcode.rpgapi.domain.abilities.Ability

data class UpdateAbilityDto(
        val id: String,
        val name: String,
        val description: String
) {
    companion object {
        fun mapToAbility(dto: UpdateAbilityDto) = Ability(dto.name, dto.description, dto.id)
    }
}