package com.mypieceofcode.rpgapi.application.mutations.dto

data class CreateMutationDto(
        val name: String,
        val type: String,
        val description: String,
        val godType: String,
        val ps: Int,
        val roll: String,
        val comment: String = "",
        val variants: String = "",
        val table: List<Pair<String, List<String>>>? = null
) {


}