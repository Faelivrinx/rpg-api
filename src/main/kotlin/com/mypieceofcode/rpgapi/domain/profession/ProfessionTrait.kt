package com.mypieceofcode.rpgapi.domain.profession

import com.mypieceofcode.rpgapi.domain.enums.Trait

data class ProfessionTrait(
        val name: Trait,
        val value: Int
) {
}