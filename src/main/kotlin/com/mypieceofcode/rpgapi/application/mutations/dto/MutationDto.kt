package com.mypieceofcode.rpgapi.application.mutations.dto

import com.mypieceofcode.rpgapi.domain.mutations.Mutation
import com.mypieceofcode.rpgapi.domain.mutations.MutationTable

data class MutationDto(
        val name: String,
        val type: String,
        val description: String,
        val godType: String,
        val table: List<Pair<String, List<String>>>,
        val id: String
) {
    companion object {
        fun from(mutation: Mutation) = MutationDto(
                mutation.name,mutation.type.toString(),
                mutation.description,
                mutation.godType,
                MutationTable.from(mutation.table),
                mutation.id!!
        )
    }
}