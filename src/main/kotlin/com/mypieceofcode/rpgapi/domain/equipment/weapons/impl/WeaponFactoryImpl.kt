package com.mypieceofcode.rpgapi.domain.equipment.weapons.impl

import com.mypieceofcode.rpgapi.application.weapons.dto.NewWeaponDto
import com.mypieceofcode.rpgapi.application.weapons.dto.UpdateWeaponDto
import com.mypieceofcode.rpgapi.domain.enums.Availability
import com.mypieceofcode.rpgapi.domain.enums.WeaponCategory
import com.mypieceofcode.rpgapi.domain.enums.WeaponType
import com.mypieceofcode.rpgapi.domain.equipment.weapons.Weapon
import com.mypieceofcode.rpgapi.domain.equipment.weapons.WeaponFactory
import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbWeapon
import org.springframework.stereotype.Component

@Component
class WeaponFactoryImpl : WeaponFactory {
    override fun <T> createWeapon(weapon: T): Weapon {
        return when (weapon) {
            is NewWeaponDto -> createNewFromDto(weapon)
            is UpdateWeaponDto -> updateWeaponFromDto(weapon)
            is DbWeapon -> createNewFromDb(weapon)
            else -> throw DomainException(ErrorCode.WEAPON_CONVERTER_EXCEPTION)
        }
    }

    private fun updateWeaponFromDto(weapon: UpdateWeaponDto): Weapon {
        val type = WeaponType.createWeaponType(weapon.type)
        return when {
            (type == WeaponType.RANGED) -> Weapon(
                    weapon.name,
                    WeaponType.createWeaponType(weapon.type),
                    WeaponCategory.createWeaponCategory(weapon.category),
                    weapon.power,
                    weapon.weaponTraits,
                    weapon.description,
                    weapon.price,
                    weapon.weight,
                    Availability.createAvailability(weapon.availability),
                    weapon.url,
                    weapon.range,
                    weapon.reloadTime,
                    weapon.id
            )
            else -> Weapon(
                    weapon.name,
                    WeaponType.createWeaponType(weapon.type),
                    WeaponCategory.createWeaponCategory(weapon.category),
                    weapon.power,
                    weapon.weaponTraits,
                    weapon.description,
                    weapon.price,
                    weapon.weight,
                    Availability.createAvailability(weapon.availability),
                    weapon.url,
                    id = weapon.id)
        }
    }

    private fun createNewFromDb(db: DbWeapon): Weapon {
        val type = WeaponType.createWeaponType(db.type)
        return when {
            (type == WeaponType.RANGED) -> Weapon(
                    db.name,
                    WeaponType.createWeaponType(db.type),
                    WeaponCategory.createWeaponCategory(db.category),
                    db.power,
                    db.weaponTraits,
                    db.description,
                    db.price,
                    db.weight,
                    Availability.createAvailability(db.availability),
                    db.url,
                    db.range!!,
                    db.reloadTime!!,
                    db.id
            )
            else -> Weapon(
                    db.name,
                    WeaponType.createWeaponType(db.type),
                    WeaponCategory.createWeaponCategory(db.category),
                    db.power,
                    db.weaponTraits,
                    db.description,
                    db.price,
                    db.weight,
                    Availability.createAvailability(db.availability),
                    db.url,
                    id = db.id)
        }
    }

    fun createNewFromDto(dto: NewWeaponDto): Weapon {
        val type = WeaponType.createWeaponType(dto.type)
        return when {
            (type == WeaponType.RANGED) -> Weapon(
                    dto.name,
                    WeaponType.createWeaponType(dto.type),
                    WeaponCategory.createWeaponCategory(dto.category),
                    dto.power,
                    dto.weaponTraits,
                    dto.description,
                    dto.price,
                    dto.weight,
                    Availability.createAvailability(dto.availability),
                    dto.url,
                    dto.range,
                    dto.reloadTime
            )
            else -> Weapon(
                    dto.name,
                    WeaponType.createWeaponType(dto.type),
                    WeaponCategory.createWeaponCategory(dto.category),
                    dto.power,
                    dto.weaponTraits,
                    dto.description,
                    dto.price,
                    dto.weight,
                    Availability.createAvailability(dto.availability),
                    dto.url)
        }
    }

}