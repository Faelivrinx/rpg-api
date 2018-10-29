package com.mypieceofcode.rpgapi.application.abilities.dto

import com.mypieceofcode.rpgapi.domain.abilities.Ability

data class NewAbilityDto(
     val name: String,
     val description: String
) {

    companion object {
        fun mapToAbility(dto: NewAbilityDto) = Ability(dto.name, dto.description)
    }
}