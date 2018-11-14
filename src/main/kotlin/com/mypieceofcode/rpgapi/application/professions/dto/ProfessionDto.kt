package com.mypieceofcode.rpgapi.application.professions.dto

import com.mypieceofcode.rpgapi.application.abilities.dto.AbilityDto
import com.mypieceofcode.rpgapi.application.armors.dto.ArmorDto
import com.mypieceofcode.rpgapi.application.items.dto.ItemDto
import com.mypieceofcode.rpgapi.application.professions.dto.response.BasicProfessionResponse
import com.mypieceofcode.rpgapi.application.professions.dto.response.OptionalSkill
import com.mypieceofcode.rpgapi.application.professions.dto.response.ProfessionTrait
import com.mypieceofcode.rpgapi.application.skills.dto.SkillDto
import com.mypieceofcode.rpgapi.application.weapons.dto.WeaponDto
import com.mypieceofcode.rpgapi.domain.profession.Profession
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidParameterException

data class ProfessionDto(
        val name: String,
        val skills: List<SkillDto>,
        val optionalSkills: List<OptionalSkill>,
        val abilities: List<AbilityDto>,
        val items: List<ItemDto>,
        val preProfession: List<BasicProfessionResponse>,
        val postProfession: List<BasicProfessionResponse>,
        val traits: List<ProfessionTrait>,
        val description: String,
        val weapons: List<WeaponDto>,
        val armors: List<ArmorDto>,
        val url: String,
        val id: String

) {

    companion object {
        fun fromProfession(profession: Profession): ProfessionDto{
            return if (profession.id != null){
                ProfessionDto(
                        profession.name,
                        profession.skills.map { SkillDto.mapToDto(it) },
                        profession.optionalSkills.map { OptionalSkill(SkillDto.mapToDto(it.first), SkillDto.mapToDto(it.second)) },
                        profession.abilities.map { AbilityDto.mapToDto(it) },
                        profession.items.map { ItemDto.fromItem(it) },
                        profession.inProfession.map { BasicProfessionResponse(it.id, it.name) },
                        profession.outProfession.map { BasicProfessionResponse(it.id, it.name) },
                        profession.traits.map { ProfessionTrait(it.name.toString(), it.value) },
                        profession.description,
                        profession.weapons.map { WeaponDto.fromWeapon(it) },
                        profession.armors.map { ArmorDto.fromArmor(it) },
                        profession.url,
                        profession.id
                )
            } else {
                throw InvalidParameterException(ErrorCode.PROFESSION_EMPTY_ID)
            }
        }
    }
}

