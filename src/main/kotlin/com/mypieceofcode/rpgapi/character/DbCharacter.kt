package com.mypieceofcode.rpgapi.character;

import com.mypieceofcode.rpgapi.infrastructure.persistence.DbAbility
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbProfession
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbSkills
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "characters")
data class DbCharacter (
        val name: String,
        val race: String,

        @DBRef
        val currentProfession: DbProfession,

        val traits: List<CharacterTraits>,

        @DBRef
        val previousProfession: DbProfession?,

        @DBRef
        val skills: List<DbSkills>,

        @DBRef
        val abilities: List<DbAbility>,

        val additionalInfo: List<AdditionalCharacterInfo>?,

        @Id val id: String? = null
) {

}