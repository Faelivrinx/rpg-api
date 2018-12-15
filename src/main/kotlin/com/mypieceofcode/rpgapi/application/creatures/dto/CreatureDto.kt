package com.mypieceofcode.rpgapi.application.creatures.dto

import com.mypieceofcode.rpgapi.application.abilities.dto.AbilityDto
import com.mypieceofcode.rpgapi.application.armors.dto.ArmorDto
import com.mypieceofcode.rpgapi.application.items.dto.ItemDto
import com.mypieceofcode.rpgapi.application.professions.dto.ProfessionDto
import com.mypieceofcode.rpgapi.application.skills.dto.SkillDto
import com.mypieceofcode.rpgapi.application.weapons.dto.WeaponDto
import com.mypieceofcode.rpgapi.domain.creatures.Creature
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidParameterException

data class CreatureDto(
        val id: String,
        val name: String,
        val race: String,
        val professionId: ProfessionDto,
        val age: Int,
        val sex: String,
        val traits: List<Pair<String, Int>>,
        val skills: List<SkillDto>,
        val abilities: List<AbilityDto>,

        val description: String ,
        val armors: List<ArmorDto> ,
        val weapons: List<WeaponDto>,
        val items: List<ItemDto>
) {
    companion object {
        fun fromCreature(c: Creature) : CreatureDto {
            if (c.id != null){
              return CreatureDto(
                      c.id,
                      c.name,
                      c.race,
                      ProfessionDto.fromProfession(c.profession),
                      c.age,
                      c.sex.toString(),
                      c.traits.map { Pair(it.name.toString(), it.value) },
                      c.skills.map { SkillDto.mapToDto(it) },
                      c.abilities.map { AbilityDto.mapToDto(it) },
                      c.description,
                      c.armors.map { ArmorDto.fromArmor(it) },
                      c.weapons.map { WeaponDto.fromWeapon(it) },
                      c.items.map { ItemDto.fromItem(it) }
              )
            }
            throw InvalidParameterException(ErrorCode.CREATURE_EMPTY_ID)
        }
    }
}