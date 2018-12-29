package com.mypieceofcode.rpgapi.domain.mutations

data class MutationTable(
        val data: Map<String, List<String>>,
        val length: Int
) {

    companion object {
        fun createEmpty() = MutationTable(mapOf(), 0)
        fun from(table: MutationTable) : List<Pair<String, List<String>>> = table.data.map { Pair(it.key, it.value) }
    }
}