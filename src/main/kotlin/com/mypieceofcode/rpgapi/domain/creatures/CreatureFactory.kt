package com.mypieceofcode.rpgapi.domain.creatures

import com.mypieceofcode.rpgapi.application.creatures.dto.CreateCreatureDto
import com.mypieceofcode.rpgapi.application.creatures.dto.UpdateCreatureDto
import com.mypieceofcode.rpgapi.domain.abilities.AbilitiesRepository
import com.mypieceofcode.rpgapi.domain.abilities.Ability
import com.mypieceofcode.rpgapi.domain.enums.Sex
import com.mypieceofcode.rpgapi.domain.enums.Trait
import com.mypieceofcode.rpgapi.domain.equipment.armors.Armor
import com.mypieceofcode.rpgapi.domain.equipment.armors.ArmorRepository
import com.mypieceofcode.rpgapi.domain.equipment.items.Item
import com.mypieceofcode.rpgapi.domain.equipment.items.ItemRepository
import com.mypieceofcode.rpgapi.domain.equipment.weapons.Weapon
import com.mypieceofcode.rpgapi.domain.equipment.weapons.WeaponRepository
import com.mypieceofcode.rpgapi.domain.profession.ProfessionRepository
import com.mypieceofcode.rpgapi.domain.profession.TraitWithValue
import com.mypieceofcode.rpgapi.domain.skills.Skill
import com.mypieceofcode.rpgapi.domain.skills.SkillRepository
import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreatureFactory {

    @Autowired
    lateinit var creatureRepo: CreatureRepository

    @Autowired
    lateinit var skillRepo: SkillRepository
    @Autowired
    lateinit var professionRepo: ProfessionRepository
    @Autowired
    lateinit var abilityRepo: AbilitiesRepository
    @Autowired
    lateinit var itemRepo: ItemRepository

    @Autowired
    lateinit var weaponRepo: WeaponRepository

    @Autowired
    lateinit var armorRepo: ArmorRepository

    fun createCreature(dto: Any): Creature {
        return when (dto) {
            is CreateCreatureDto -> createNewCreature(dto)
            is UpdateCreatureDto -> updateExistingCreature(dto)
            else -> throw  DomainException(ErrorCode.CREATURE_CREATION_UNSUPPORTED)
        }
    }


    private fun createNewCreature(dto: CreateCreatureDto): Creature {
        val skills = getSkills(dto.skillsId)
        val abilities = getAbilities(dto.abilitiesId)
        val armors = getArmors(dto.armorsId)
        val items = getItems(dto.itemsId)
        val weapons = getWeapons(dto.weaponsId)

        return Creature(dto.name,
                dto.type,
                dto.traits.map { TraitWithValue(Trait.createTrait(it.first), it.second) }.toMutableList(),
                skills.toMutableList(),
                abilities.toMutableList(),
                dto.description,
                dto.specialRules,
                armors.toMutableList(),
                weapons.toMutableList(),
                items.toMutableList()
                )
    }

    private fun updateExistingCreature(dto: UpdateCreatureDto): Creature {
        if (!creatureRepo.existsById(dto.id)){
            throw MissingEntityException(ErrorCode.CREATURE_NOT_FOUND)
        }
        val skills = getSkills(dto.skillsId)
        val abilities = getAbilities(dto.abilitiesId)
        val armors = getArmors(dto.armorsId)
        val items = getItems(dto.itemsId)
        val weapons = getWeapons(dto.weaponsId)

        return Creature(dto.name,
                dto.type,
                dto.traits.map { TraitWithValue(Trait.createTrait(it.first), it.second) }.toMutableList(),
                skills.toMutableList(),
                abilities.toMutableList(),
                dto.description,
                dto.specialRules,
                armors.toMutableList(),
                weapons.toMutableList(),
                items.toMutableList(),
                dto.id
        )
    }

    private fun getWeapons(ids: List<String>): List<Weapon> = ids.distinct().map {
        val weapon = weaponRepo.findById(it)
        if (weapon.isPresent){
            weapon.get()
        } else {
            throw MissingEntityException(ErrorCode.WEAPON_NOT_FOUND)
        }
    }

    fun getSkills(ids: List<String>): List<Skill> = ids.distinct().map {
        val skill = skillRepo.findById(it)
        if (skill.isPresent) {
            skill.get()
        } else {
            throw MissingEntityException(ErrorCode.SKILL_NOT_FOUND)
        }
    }

    fun getAbilities(ids: List<String>): List<Ability> = ids.distinct().map {
        val ability = abilityRepo.findById(it)
        if (ability.isPresent) {
            ability.get()
        } else {
            throw MissingEntityException(ErrorCode.ABILITY_NOT_FOUND)
        }
    }

    private fun getArmors(ids: List<String>): List<Armor> = ids.distinct().map {
        val armor = armorRepo.findById(it)
        if (armor.isPresent){
            armor.get()
        } else {
            throw MissingEntityException(ErrorCode.ARMOR_NOT_FOUND)
        }
    }

    fun getItems(ids: List<String>): List<Item> = ids.distinct().map {
        val item = itemRepo.findById(it)
        if (item.isPresent) {
            item.get()
        } else {
            throw MissingEntityException(ErrorCode.ITEM_NOT_FOUND)
        }
    }
}