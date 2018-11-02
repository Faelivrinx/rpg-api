package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.abilities.Ability
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ability")
data class DbAbility(
        @Indexed val name: String,
        val description: String,
        @Id val id: String
) {

    companion object {
        fun toAbility(db: DbAbility) = Ability(db.name, db.description, db.id)
        fun fromAbility(ability: Ability) = DbAbility(ability.name, ability.description, ability.id)
    }

}