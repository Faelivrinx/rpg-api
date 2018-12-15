package com.mypieceofcode.rpgapi.application.creatures.dto

data class UpdateCreatureDto(
        val id: String,
        val name: String,
        val race: String,
        val professionId: String,
        val age: Int,
        val sex: String,
        val traits: List<Pair<String, Int>>,
        val skillsId: List<String>,
        val abilitiesId: List<String>,

        val description: String,
        val armorsId: List<String>,
        val weaponsId: List<String>,
        val itemsId: List<String>
) {
}