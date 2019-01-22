package com.mypieceofcode.rpgapi.character

data class CharacterTraits(
        val name: String?,
        val displayName: String,
        val baseValue: Int,
        val currentValue: Int,
        val maxProgress: Int,
        val progressStep: Int
) {
}