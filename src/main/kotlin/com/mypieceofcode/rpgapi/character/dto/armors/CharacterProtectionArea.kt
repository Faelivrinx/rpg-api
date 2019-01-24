package com.mypieceofcode.rpgapi.character.dto.armors

import com.mypieceofcode.rpgapi.domain.enums.ProtectionArea

data class CharacterProtectionArea (
        val area: ProtectionArea,
        val pz: Int
) {
}