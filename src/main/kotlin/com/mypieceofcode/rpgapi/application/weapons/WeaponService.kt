package com.mypieceofcode.rpgapi.application.weapons

import com.mypieceofcode.rpgapi.application.weapons.dto.NewWeaponDto
import com.mypieceofcode.rpgapi.application.weapons.dto.UpdateWeaponDto
import com.mypieceofcode.rpgapi.application.weapons.dto.WeaponDto
import com.mypieceofcode.rpgapi.domain.equipment.weapons.WeaponFactory
import com.mypieceofcode.rpgapi.domain.equipment.weapons.WeaponRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Service

@Service
class WeaponService(
        val repository: WeaponRepository,
        val factory: WeaponFactory
) {

    fun getWeapons(): List<WeaponDto> = repository.findAll().map { WeaponDto.fromWeapon(it) }

    fun createWeapon(dto: NewWeaponDto) {
        when (repository.existsByName(dto.name)) {
            true -> {
                throw EntityAlreadyExistsException(ErrorCode.WEAPON_ALREADY_EXISTS)
            }
            else -> repository.create(factory.createWeapon(dto))
        }
    }

    fun getWeaponById(id: String): WeaponDto {
        val weapon = repository.findById(id)
        return if (weapon.isPresent) {
            WeaponDto.fromWeapon(weapon.get())
        } else {
            throw MissingEntityException(ErrorCode.WEAPON_NOT_FOUND)
        }
    }

    fun updateWeapon(dto: UpdateWeaponDto) {
        val skill = repository.findById(dto.id)
        if (skill.isPresent) {
            repository.update(factory.createWeapon(dto))
        } else {
            throw MissingEntityException(ErrorCode.WEAPON_NOT_FOUND)
        }
    }

    fun deleteWeapon(id: String) {
        when (repository.existsById(id)) {
            true -> {
                repository.deleteById(id)
            }
            else -> {
                throw MissingEntityException(ErrorCode.WEAPON_NOT_FOUND)
            }
        }
    }
}