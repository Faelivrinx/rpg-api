package com.mypieceofcode.rpgapi.application.spells.dto

import com.mypieceofcode.rpgapi.domain.spells.Spell

data class CreateSpellDto(
        val name: String,
        val powerLevel: Int,
        val castTime: String,
        val component: String,
        val description: String,

        val table: List<Pair<String, String>> = emptyList(),
        val duration: String = ""
) {

    companion object {
        fun toSpell(dto: CreateSpellDto) = Spell(
                dto.name,
                dto.powerLevel,
                dto.castTime,
                dto.component,
                dto.description,
                dto.table,
                dto.duration
        )
    }
}