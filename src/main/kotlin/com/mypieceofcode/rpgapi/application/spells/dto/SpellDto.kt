package com.mypieceofcode.rpgapi.application.spells.dto

import com.mypieceofcode.rpgapi.domain.spells.Spell

data class SpellDto(
        val name: String,
        val powerLevel: Int,
        val castTime: String,
        val component: String,
        val description: String,

        val table: List<Pair<String, String>>,
        val duration: String,
        val id: String
) {
    companion object {
        fun from(spell: Spell) = SpellDto(
                spell.name,
                spell.powerLevel,
                spell.castTime,
                spell.component,
                spell.description,
                spell.table,
                spell.duration,
                spell.id!!
        )
    }
}