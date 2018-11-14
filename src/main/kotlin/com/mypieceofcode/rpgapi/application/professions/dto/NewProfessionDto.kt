package com.mypieceofcode.rpgapi.application.professions.dto

import com.mypieceofcode.rpgapi.application.professions.dto.request.NewOptionalSkill
import com.mypieceofcode.rpgapi.application.professions.dto.response.ProfessionTrait

data class NewProfessionDto(
        val name: String ,
        val skills: List<String>,
        val abilities: List<String>,
        val optionalSkills: List<NewOptionalSkill> = emptyList(),
        val items: List<String> = emptyList(),
        val preProfession: List<String> = emptyList(),
        val postProfession: List<String> = emptyList(),
        val traits: List<ProfessionTrait> = emptyList(),
        val weapons: List<String> = emptyList(),
        val armors: List<String> = emptyList(),
        val description: String = "",
        val url: String = ""
) {

}