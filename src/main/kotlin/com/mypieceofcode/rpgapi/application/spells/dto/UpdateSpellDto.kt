package com.mypieceofcode.rpgapi.application.spells.dto

import com.mypieceofcode.rpgapi.domain.spells.Spell

class UpdateSpellDto(val name: String,
                     val powerLevel: Int,
                     val castTime: String,
                     val component: String,
                     val description: String,
                     val table: List<Pair<String, String>>,
                     val duration: String,
                     val id: String
) {

    companion object {
        fun toSpell(dto: UpdateSpellDto) = Spell(
                dto.name,
                dto.powerLevel,
                dto.castTime,
                dto.component,
                dto.description,
                dto.table,
                dto.duration,
                dto.id
        )
    }
}