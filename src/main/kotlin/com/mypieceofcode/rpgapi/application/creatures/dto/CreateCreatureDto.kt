package com.mypieceofcode.rpgapi.application.creatures.dto

data class CreateCreatureDto(
        val name: String,
        val type: String,
        val traits: List<Pair<String, Int>>,
        val skillsId: List<String>,
        val abilitiesId: List<String>,

        val imageName: String = "",
        val description: String = "",
        val specialRules: String = "",
        val armorsId: List<String> = emptyList(),
        val weaponsId: List<String> = emptyList(),
        val itemsId: List<String> = emptyList()
) {

}