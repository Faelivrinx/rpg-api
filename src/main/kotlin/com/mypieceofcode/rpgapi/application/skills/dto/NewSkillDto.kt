package com.mypieceofcode.rpgapi.application.skills.dto

import com.mypieceofcode.rpgapi.domain.enums.SkillType
import com.mypieceofcode.rpgapi.domain.enums.Trait
import com.mypieceofcode.rpgapi.domain.skills.Skill

data class NewSkillDto(
        val name: String,
        val description: String,
        val trait: String,
        val type: String
) {

    companion object {
        fun mapToSkill(dto: NewSkillDto) = Skill(dto.name, dto.description, Trait.createTrait(dto.trait), SkillType.createSkillType(dto.type))
    }
}