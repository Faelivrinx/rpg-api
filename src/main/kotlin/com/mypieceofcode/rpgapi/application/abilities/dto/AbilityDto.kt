package com.mypieceofcode.rpgapi.application.abilities.dto

import com.mypieceofcode.rpgapi.domain.abilities.Ability

data class AbilityDto(
        val id: String,
        val name: String,
        val description: String
) {
    companion object {
        fun mapToDto(ability: Ability) = AbilityDto( ability.id, ability.name, ability.description)
    }
}