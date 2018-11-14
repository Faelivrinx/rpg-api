package com.mypieceofcode.rpgapi.application.professions.dto

import com.mypieceofcode.rpgapi.application.professions.dto.request.NewOptionalSkill
import com.mypieceofcode.rpgapi.application.professions.dto.response.ProfessionTrait

data class UpdateProfessionDto(
        val name: String,
        val skills: List<String>,
        val abilities: List<String>,
        val optionalSkills: List<NewOptionalSkill>,
        val items: List<String>,
        val preProfession: List<String>,
        val postProfession: List<String>,
        val traits: List<ProfessionTrait>,
        val weapons: List<String>,
        val armors: List<String>,
        val description: String,
        val url: String,
        val id: String
) {
}