package com.mypieceofcode.rpgapi.character.dto.info

import com.mypieceofcode.rpgapi.character.model.AdditionalCharacterInfo
import com.mypieceofcode.rpgapi.character.model.CharacterTraits
import com.mypieceofcode.rpgapi.character.DbCharacter
import com.mypieceofcode.rpgapi.character.model.CharacterAbility
import com.mypieceofcode.rpgapi.character.model.CharacterSkill

data class CharacterDto(
        val id: String,
        val name: String,
        val race: String,
        val xp: Long,

        val currentProfession: String,
        val traits: List<CharacterTraits>,
        val skills: List<CharacterSkill>,
        val abilities: List<CharacterAbility>,

        val previousProfession: String?,
        val additionalInfo: List<AdditionalCharacterInfo>?

) {

    companion object {
        fun to(character: DbCharacter): CharacterDto {
            return CharacterDto(
                    character.id!!,
                    character.name,
                    character.race,
                    character.xp,
                    character.currentProfession.name,
                    character.traits,
                    character.skills.map { CharacterSkill.to(it) },
                    character.abilities.map { CharacterAbility.to(it) },
                    character.previousProfession?.name,
                    character.additionalInfo)
        }
    }

}