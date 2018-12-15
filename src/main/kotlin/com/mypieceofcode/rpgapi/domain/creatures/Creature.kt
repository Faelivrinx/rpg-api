package com.mypieceofcode.rpgapi.domain.creatures

import com.mypieceofcode.rpgapi.domain.abilities.Ability
import com.mypieceofcode.rpgapi.domain.enums.Sex
import com.mypieceofcode.rpgapi.domain.equipment.armors.Armor
import com.mypieceofcode.rpgapi.domain.equipment.items.Item
import com.mypieceofcode.rpgapi.domain.equipment.weapons.Weapon
import com.mypieceofcode.rpgapi.domain.profession.Profession
import com.mypieceofcode.rpgapi.domain.profession.TraitWithValue
import com.mypieceofcode.rpgapi.domain.skills.Skill
import org.springframework.data.annotation.Id

data class Creature(
        val name: String,
        val race: String,
        val profession: Profession,
        val age: Int,
        val sex: Sex,
        val traits: MutableList<TraitWithValue>,
        val skills: MutableList<Skill>,
        val abilities: MutableList<Ability>,

        val description: String = "",
        val armors : MutableList<Armor> = mutableListOf(),
        val weapons: MutableList<Weapon> = mutableListOf(),
        val items: MutableList<Item> = mutableListOf(),

        @Id val id: String? = null
) {
}