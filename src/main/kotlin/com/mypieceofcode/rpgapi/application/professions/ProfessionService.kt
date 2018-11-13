package com.mypieceofcode.rpgapi.application.professions

import com.mypieceofcode.rpgapi.application.professions.dto.NewProfessionDto
import com.mypieceofcode.rpgapi.application.professions.dto.ProfessionDto
import com.mypieceofcode.rpgapi.application.weapons.dto.UpdateWeaponDto
import com.mypieceofcode.rpgapi.domain.profession.ProfessionFactory
import com.mypieceofcode.rpgapi.domain.profession.ProfessionRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Service

@Service
class ProfessionService(
        private val repository: ProfessionRepository,
        private val factory: ProfessionFactory
) {
    fun getProfessions(): List<ProfessionDto> = repository.findAll().map { ProfessionDto.fromProfession(it) }

    fun createProfession(dto: NewProfessionDto) {

        if (repository.existsByName(dto.name)) {
            throw EntityAlreadyExistsException(ErrorCode.PROFESSION_ALREADY_EXISTS)
        } else {
            val profession = factory.createProfession(dto)
            repository.create(profession)
        }
    }

    fun getProfessionById(id: String): ProfessionDto {
        val profession = repository.findById(id)
        return if (profession.isPresent) {
            ProfessionDto.fromProfession(profession.get())
        } else {
            throw MissingEntityException(ErrorCode.PROFESSION_NOT_FOUND)
        }
    }

    fun updateWeapon(dto: UpdateWeaponDto) {
//        val skill = repository.findById(dto.id)
//        if (skill.isPresent) {
//            repository.update(factory.createWeapon(dto))
//        } else {
//            throw MissingEntityException(ErrorCode.WEAPON_NOT_FOUND)
//        }
    }

    fun deleteProfession(id: String) {
        when (repository.existsById(id)) {
            true -> {
                repository.deleteById(id)
            }
            else -> {
                throw MissingEntityException(ErrorCode.PROFESSION_NOT_FOUND)
            }
        }
    }
}