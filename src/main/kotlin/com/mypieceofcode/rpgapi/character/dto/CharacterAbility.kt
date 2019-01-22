package com.mypieceofcode.rpgapi.character.dto

import com.mypieceofcode.rpgapi.infrastructure.persistence.DbAbility

data class CharacterAbility(
        val id: String,
        val name: String,
        val description: String
) {
    companion object {
        fun to (db: DbAbility) : CharacterAbility {
            return CharacterAbility(db.id!!, db.name, db.description)
        }
    }
}