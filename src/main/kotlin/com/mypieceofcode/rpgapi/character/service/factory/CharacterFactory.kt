package com.mypieceofcode.rpgapi.character.service.factory

import com.mypieceofcode.rpgapi.character.DbCharacter
import com.mypieceofcode.rpgapi.character.dto.armors.CharacterArmorDto
import com.mypieceofcode.rpgapi.character.dto.armors.CharacterProtectionArea
import com.mypieceofcode.rpgapi.domain.enums.ProtectionArea
import org.springframework.stereotype.Component

@Component
class CharacterFactory {
    fun createArmors(db: DbCharacter): List<CharacterArmorDto> {
        return db.armors?.map { armor ->
            CharacterArmorDto(armor.name, true,
                    armor.protectionAreas.map { CharacterProtectionArea(ProtectionArea.createArea(it), armor.pz) })
        }.orEmpty()
    }
}