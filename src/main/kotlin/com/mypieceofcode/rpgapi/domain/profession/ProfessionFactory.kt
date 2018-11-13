package com.mypieceofcode.rpgapi.domain.profession

import com.mypieceofcode.rpgapi.application.professions.dto.NewProfessionDto
import com.mypieceofcode.rpgapi.application.professions.dto.request.NewOptionalSkill
import com.mypieceofcode.rpgapi.domain.abilities.AbilitiesRepository
import com.mypieceofcode.rpgapi.domain.abilities.Ability
import com.mypieceofcode.rpgapi.domain.enums.Trait
import com.mypieceofcode.rpgapi.domain.equipment.items.Item
import com.mypieceofcode.rpgapi.domain.equipment.items.ItemRepository
import com.mypieceofcode.rpgapi.domain.skills.Skill
import com.mypieceofcode.rpgapi.domain.skills.SkillRepository
import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProfessionFactory {

    @Autowired
    lateinit var skillRepo: SkillRepository
    @Autowired
    lateinit var professionRepo: ProfessionRepository
    @Autowired
    lateinit var abilityRepo: AbilitiesRepository
    @Autowired
    lateinit var itemRepo: ItemRepository

    fun createProfession(dto: Any): Profession {
        return when (dto) {
            is NewProfessionDto -> createNewProfession(dto)
            else -> throw DomainException(ErrorCode.PROFESSION_CREATION_UNSUPPORTED)
        }
    }

    private fun createNewProfession(dto: NewProfessionDto): Profession {
        val abilities = getAbilities(dto.abilities)
        val skills = getSkills(dto.skills)
        val optionalSkills = getOptionalSkill(dto.optionalSkills)
        val items = getItems(dto.items)
        val preProfession = getProfession(dto.preProfession)
        val postProfession = getProfession(dto.postProfession)
        val traits = dto.traits.map { ProfessionTrait(Trait.createTrait(it.traitName), it.traitExtend) }.toCollection(ArrayList())
        return Profession(dto.name, dto.description, skills, optionalSkills, abilities, items, preProfession, postProfession, traits, dto.url)
    }

    fun getSkills(ids: List<String>): List<Skill> = ids.map {
        val skill = skillRepo.findById(it)
        if (skill.isPresent) {
            skill.get()
        } else {
            throw MissingEntityException(ErrorCode.SKILL_NOT_FOUND)
        }
    }

    fun getAbilities(ids: List<String>): List<Ability> = ids.map {
        val ability = abilityRepo.findById(it)
        if (ability.isPresent) {
            ability.get()
        } else {
            throw MissingEntityException(ErrorCode.ABILITY_NOT_FOUND)
        }
    }

    fun getItems(ids: List<String>): List<Item> = ids.map {
        val item = itemRepo.findById(it)
        if (item.isPresent) {
            item.get()
        } else {
            throw MissingEntityException(ErrorCode.ITEM_NOT_FOUND)
        }
    }

    fun getProfession(ids: List<String>): List<BasicProfession> = ids.map {
        val profession = professionRepo.findById(it)
        if (profession.isPresent) {
            val get = profession.get()
            BasicProfession(get.name, get.id!!)
        } else {
            throw MissingEntityException(ErrorCode.PROFESSION_NOT_FOUND)
        }
    }

    fun getOptionalSkill(optionalSkills: List<NewOptionalSkill>): List<OptionalSkill> {
        val skillList: MutableList<OptionalSkill> = mutableListOf()
        optionalSkills.forEach {
            val first = skillRepo.findById(it.firstId)
            val second = skillRepo.findById(it.secondId)
            if (first.isPresent && second.isPresent) {
                skillList.add(OptionalSkill(first.get(), second.get()))
            } else {
                throw MissingEntityException(ErrorCode.SKILL_NOT_FOUND)
            }
        }
        return skillList
    }

}
