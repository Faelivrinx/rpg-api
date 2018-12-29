package com.mypieceofcode.rpgapi.application.spells

import com.mypieceofcode.rpgapi.application.spells.dto.CreateSpellDto
import com.mypieceofcode.rpgapi.application.spells.dto.SpellDto
import com.mypieceofcode.rpgapi.application.spells.dto.UpdateSpellDto
import com.mypieceofcode.rpgapi.domain.spells.SpellRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Service

@Service
class SpellService(
        val repository: SpellRepository
) {
    fun getSpells(): List<SpellDto> = repository.findAll().map { SpellDto.from(it) }

    fun createSpell(dto: CreateSpellDto) {
        when (repository.existsByName(dto.name)) {
            true -> {
                throw EntityAlreadyExistsException(ErrorCode.SPELL_ALREADY_EXISTS)
            }
            else -> repository.create(CreateSpellDto.toSpell(dto))
        }
    }

    fun getSpellById(id: String): SpellDto {
        val spell = repository.findById(id)
        return if (spell.isPresent) {
            SpellDto.from(spell.get())
        } else {
            throw MissingEntityException(ErrorCode.SPELL_NOT_FOUND)
        }
    }

    fun updateSpell(dto: UpdateSpellDto) {
        val spell = repository.findById(dto.id)
        if (spell.isPresent) {
            repository.update(UpdateSpellDto.toSpell(dto))
        } else {
            throw MissingEntityException(ErrorCode.SPELL_NOT_FOUND)
        }
    }

    fun deleteSpell(id: String) {
        when (repository.existsById(id)) {
            true -> {
                repository.deleteById(id)
            }
            else -> {
                throw MissingEntityException(ErrorCode.SPELL_NOT_FOUND)
            }
        }
    }
}