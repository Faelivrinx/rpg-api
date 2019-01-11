package com.mypieceofcode.rpgapi.domain.mutations

data class Mutation(
        val name: String,
        val type: MutationType,
        val description: String,
        val godType: Gods,
        val ps: Int,
        val roll: String,
        val comment: String,
        val variants: String,
        val table: MutationTable = MutationTable.createEmpty(),
        val id: String? = null
) {
}