package com.mypieceofcode.rpgapi.domain.profession

import com.mypieceofcode.rpgapi.domain.enums.Trait

data class TraitWithValue(
        val name: Trait,
        val baseValue: Int,
        val extendValue: Int?
) {
}