package com.mypieceofcode.rpgapi.character.model

import com.mypieceofcode.rpgapi.infrastructure.persistence.DbSkills


data class CharacterSkill(
        val id: String,
        val name: String,
        val description: String
) {
    companion object {
        fun to (db: DbSkills) : CharacterSkill {
            return CharacterSkill(db.id!!, db.name, db.description)
        }
    }
}