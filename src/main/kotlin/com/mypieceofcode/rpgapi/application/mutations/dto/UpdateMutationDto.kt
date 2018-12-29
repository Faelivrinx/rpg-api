package com.mypieceofcode.rpgapi.application.mutations.dto

data class UpdateMutationDto(
        val name: String,
        val type: String,
        val description: String,
        val godType: String,
        val table: List<Pair<String, List<String>>>,
        val id: String
) {
}