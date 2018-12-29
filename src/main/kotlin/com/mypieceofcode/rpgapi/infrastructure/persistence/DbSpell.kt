package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.spells.Spell
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "spells")
class DbSpell(
        val name: String,
        val powerLevel: Int,
        val castTime: String,
        val component: String,
        val description: String,

        val table: List<Pair<String, String>> = emptyList(),
        val duration: String = "",
        @Id val id: String? = null
) {

    companion object {
        fun from(db: DbSpell) = Spell(
                db.name,
                db.powerLevel,
                db.castTime,
                db.component,
                db.description,
                db.table,
                db.duration,
                db.id
        )

        fun to(spell: Spell) = DbSpell(
                spell.name,
                spell.powerLevel,
                spell.castTime,
                spell.component,
                spell.description,
                spell.table,
                spell.duration,
                spell.id
        )
    }

}