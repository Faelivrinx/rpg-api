package com.mypieceofcode.rpgapi.domain.mutations

data class Mutation(
        val name: String,
        val type: MutationType,
        val description: String,
        val godType: String,
        val table: MutationTable = MutationTable.createEmpty(),
        val id: String? = null
) {
}