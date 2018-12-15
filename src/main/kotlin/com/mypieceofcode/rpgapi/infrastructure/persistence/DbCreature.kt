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

        @DBRef
        val profession: DbProfession,

        val age: Int,
        val sex: Int,
        val traits: MutableList<TraitWithValue>,
        val skills: MutableList<DbSkills>,
        val abilities: MutableList<DbAbility>,

        val description: String = "",
        val armors: MutableList<DbArmor> = mutableListOf(),
        val weapons: MutableList<DbWeapon> = mutableListOf(),
        val items: MutableList<DbItem> = mutableListOf(),

        @Id val id: String? = null
) {

        companion object {
            fun fromDb(db: DbCreature) = Creature(db.name,
                    db.race,
                    DbProfession.toProfession(db.profession),
                    db.age,
                    Sex.fromDb(db.sex),
                    db.traits,
                    db.skills.map { DbSkills.toSkill(it) }.toMutableList(),
                    db.abilities.map { DbAbility.toAbility(it) }.toMutableList(),
                    db.description,
                    db.armors.map { DbArmor.toArmor(it) }.toMutableList(),
                    db.weapons.map { DbWeapon.toWeapon(it) }.toMutableList(),
                    db.items.map { DbItem.toItem(it) }.toMutableList(),
                    db.id
                    )
        }
}