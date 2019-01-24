package com.mypieceofcode.rpgapi.character.dto.armors

import com.mypieceofcode.rpgapi.domain.enums.ProtectionArea


data class CharacterArmorDto(
        val name: String,
        val isActive: Boolean,
        val protectionArea: List<CharacterProtectionArea>
) {
}