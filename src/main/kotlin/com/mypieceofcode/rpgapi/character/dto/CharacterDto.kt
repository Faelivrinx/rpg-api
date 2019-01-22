package com.mypieceofcode.rpgapi.character.dto

import com.mypieceofcode.rpgapi.character.AdditionalCharacterInfo
import com.mypieceofcode.rpgapi.character.CharacterTraits
import com.mypieceofcode.rpgapi.character.DbCharacter

data class CharacterDto(
        val id: String,
        val name: String,
        val race: String,

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
                    character.currentProfession.name,
                    character.traits,
                    character.skills.map { CharacterSkill.to(it) },
                    character.abilities.map { CharacterAbility.to(it) },
                    character.previousProfession?.name,
                    character.additionalInfo)
        }
    }

}