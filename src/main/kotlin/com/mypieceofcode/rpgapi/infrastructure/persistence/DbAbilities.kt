package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.abilities.Ability
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.annotation.Generated

@Document
data class DbAbilities(
        @Indexed val name: String,
        val description: String,
        @Id val id: String
) {

    companion object {
        fun toAbility(db: DbAbilities) = Ability(db.name, db.description, db.id)
        fun fromAbility(ability: Ability) = DbAbilities(ability.name, ability.description, ability.id)
    }

}