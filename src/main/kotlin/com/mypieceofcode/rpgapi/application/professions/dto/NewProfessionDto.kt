package com.mypieceofcode.rpgapi.application.professions.dto

import com.mypieceofcode.rpgapi.application.professions.dto.request.NewOptionalSkill
import com.mypieceofcode.rpgapi.application.professions.dto.response.ProfessionTrait

data class NewProfessionDto(
        val name: String ,
        val skills: List<String> = emptyList(),
        val optionalSkills: List<NewOptionalSkill> = emptyList(),
        val abilities: List<String> = emptyList(),
        val items: List<String> = emptyList(),
        val preProfession: List<String> = emptyList(),
        val postProfession: List<String> = emptyList(),
        val traits: List<ProfessionTrait> = emptyList(),
        val description: String = "",
        val url: String = ""
) {

}