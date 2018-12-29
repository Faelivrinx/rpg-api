package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.creatures.Creature
import com.mypieceofcode.rpgapi.domain.enums.Sex
import com.mypieceofcode.rpgapi.domain.equipment.armors.Armor
import com.mypieceofcode.rpgapi.domain.profession.TraitWithValue
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "creatures")
class DbCreature(
        val name: String,
        val race: String,


        val traits: MutableList<TraitWithValue>,

        @DBRef
        val skills: MutableList<DbSkills>,

        @DBRef
        val abilities: MutableList<DbAbility>,

        val description: String = "",
        val specialRules: String = "",

        @DBRef
        val armors: MutableList<DbArmor> = mutableListOf(),

        @DBRef
        val weapons: MutableList<DbWeapon> = mutableListOf(),

        @DBRef
        val items: MutableList<DbItem> = mutableListOf(),

        @Id val id: String? = null
) {

        companion object {
            fun fromDb(db: DbCreature) = Creature(db.name,
                    db.race,
                    db.traits,
                    db.skills.map { DbSkills.toSkill(it) }.toMutableList(),
                    db.abilities.map { DbAbility.toAbility(it) }.toMutableList(),
                    db.description,
                    db.specialRules,
                    db.armors.map { DbArmor.toArmor(it) }.toMutableList(),
                    db.weapons.map { DbWeapon.toWeapon(it) }.toMutableList(),
                    db.items.map { DbItem.toItem(it) }.toMutableList(),
                    db.id
                    )
        }
}