package com.mypieceofcode.rpgapi.domain.spells

data class Spell(
        val name: String,
        val powerLevel: Int,
        val castTime: String,
        val component: String,
        val description: String,

        val table: List<Pair<String, String>> = emptyList(),
        val duration: String = "",
        val id: String? = null
) {
}