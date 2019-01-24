package com.mypieceofcode.rpgapi.character.model

data class CharacterTraits(
        val name: String?,
        val displayName: String,
        val baseValue: Int,
        var currentValue: Int,
        val maxProgress: Int,
        val progressStep: Int
) {
}