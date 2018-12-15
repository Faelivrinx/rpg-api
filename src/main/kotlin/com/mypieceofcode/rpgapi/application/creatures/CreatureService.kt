package com.mypieceofcode.rpgapi.application.creatures

import com.mypieceofcode.rpgapi.application.creatures.dto.CreateCreatureDto
import com.mypieceofcode.rpgapi.application.creatures.dto.CreatureDto
import com.mypieceofcode.rpgapi.application.creatures.dto.UpdateCreatureDto
import com.mypieceofcode.rpgapi.domain.creatures.CreatureFactory
import com.mypieceofcode.rpgapi.domain.creatures.CreatureRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Component

@Component
class CreatureService(
        val factory: CreatureFactory,
        val creatureRepo: CreatureRepository
) {

    fun createNewCreature(dto: CreateCreatureDto) {
        if (creatureRepo.existsByName(dto.name)) {
            throw EntityAlreadyExistsException(ErrorCode.CREATURE_ALREADY_EXISTS)
        }

        val creature = factory.createCreature(dto)
        creatureRepo.create(creature)
    }

    fun updateCreature(dto: UpdateCreatureDto) {
        if (!creatureRepo.existsById(dto.id)) {
            throw MissingEntityException(ErrorCode.CREATURE_NOT_FOUND)
        }

        val creature = factory.createCreature(dto)
        creatureRepo.update(creature)
    }

    fun deleteCreature(id: String) {
        if (!creatureRepo.existsById(id)){
            throw MissingEntityException(ErrorCode.CREATURE_NOT_FOUND)
        }
        creatureRepo.deleteById(id)
    }

    fun getCreatures() = creatureRepo.findAll().map { CreatureDto.fromCreature(it) }

    fun getCreatureById(id: String) : CreatureDto {
        val creature = creatureRepo.findById(id).orElseThrow { MissingEntityException(ErrorCode.CREATURE_NOT_FOUND) }
        return CreatureDto.fromCreature(creature)
    }

}