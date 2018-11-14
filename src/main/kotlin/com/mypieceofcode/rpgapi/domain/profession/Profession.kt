package com.mypieceofcode.rpgapi.domain.profession

import com.mypieceofcode.rpgapi.domain.DomainObject
import com.mypieceofcode.rpgapi.domain.abilities.Ability
import com.mypieceofcode.rpgapi.domain.equipment.armors.Armor
import com.mypieceofcode.rpgapi.domain.equipment.items.Item
import com.mypieceofcode.rpgapi.domain.equipment.weapons.Weapon
import com.mypieceofcode.rpgapi.domain.skills.Skill
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException
import org.apache.commons.lang3.StringUtils.isBlank

class Profession(
        val name: String,
        val description: String = "",
        val skills: List<Skill>,
        val abilities: List<Ability>,
        val optionalSkills: List<OptionalSkill> = emptyList(),
        val items: List<Item> = emptyList(),
        val inProfession: List<BasicProfession> = emptyList(),
        val outProfession: List<BasicProfession> = emptyList(),
        val traits: List<ProfessionTrait> = emptyList(),
        val weapons: List<Weapon> = emptyList(),
        val armors: List<Armor> = emptyList(),
        val url: String = "",
        val id: String? = null

) : DomainObject() {

    init {
        validate()
    }

    override fun validate() {
        validateEmpty()
    }

    private fun validateEmpty() {
        if (isBlank(this.name)){
            throw InvalidEntityException(ErrorCode.WEAPON_NOT_FOUND)
        }
    }
}