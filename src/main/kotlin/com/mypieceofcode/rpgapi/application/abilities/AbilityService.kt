package com.mypieceofcode.rpgapi.application.abilities

import com.mypieceofcode.rpgapi.application.abilities.dto.AbilityDto
import com.mypieceofcode.rpgapi.application.abilities.dto.NewAbilityDto
import com.mypieceofcode.rpgapi.application.abilities.dto.UpdateAbilityDto
import com.mypieceofcode.rpgapi.domain.abilities.AbilitiesRepository
import com.mypieceofcode.rpgapi.exceptions.EntityException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Service

@Service
class AbilityService(
        val repository: AbilitiesRepository
) {

    fun getAbilities(): List<AbilityDto> = repository.findAll().map { AbilityDto.mapToDto(it) }

    fun getAbilityById(id: String): AbilityDto {
        val ability = repository.findById(id)
        if (ability.isPresent){
            return AbilityDto.mapToDto(ability.get())
        }
        throw MissingEntityException(ErrorCode.ABILITY_NOT_FOUND)
    }

    fun createAbility(dto: NewAbilityDto) {
        val ability = NewAbilityDto.mapToAbility(dto)
        when (repository.exists(ability.name)) {
            true -> {
                throw MissingEntityException(ErrorCode.ABILITY_ALREADY_EXISTS)
            }
            else -> {
                repository.createAbility(ability)
            }
        }
    }

    fun updateAbility(dto: UpdateAbilityDto){
        val ability = UpdateAbilityDto.mapToAbility(dto)
        when(repository.existsById(ability.id)){
            true -> {
                repository.updateAbility(ability)
            }
            else -> {
                throw MissingEntityException(ErrorCode.ABILITY_NOT_FOUND)
            }
        }
    }

    fun deleteAbility(id: String){
        when (repository.findById(id).isPresent){
            true -> repository.delete(id)
            else -> throw MissingEntityException(ErrorCode.ABILITY_NOT_FOUND)
        }
    }

}