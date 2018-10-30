package com.mypieceofcode.rpgapi.application.skills.dto

import com.mypieceofcode.rpgapi.domain.skills.Skill
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException

data class SkillDto(
        val id: String,
        val name: String,
        val description: String,
        val trait: String,
        val type: String
){
    companion object {
        fun mapToDto(skill: Skill): SkillDto {
            return if (skill.id != null){
                SkillDto(skill.id, skill.name, skill.description, skill.trait.toString(), skill.type.toString())
            } else {
                throw InvalidEntityException(ErrorCode.SKILL_EMPTY_ID)
            }
        }
    }
}