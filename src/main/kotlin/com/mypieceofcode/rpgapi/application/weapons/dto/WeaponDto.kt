package com.mypieceofcode.rpgapi.application.weapons.dto

import com.mypieceofcode.rpgapi.domain.equipment.weapons.Weapon
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException


data class WeaponDto(
        val name: String,
        val type: String,
        val category: String,
        val power: String,
        val weaponTraits: List<String>,
        val description: String ,
        val price: Float ,
        val weight: Float,
        val availability: String,
        val url: String ,
        val range: Float?  = null ,
        val reloadTime: String? = null ,
        val id: String
) {

    companion object {
        fun fromWeapon(weapon: Weapon) : WeaponDto {
            return if (weapon.id != null ){
                WeaponDto(
                        weapon.name,
                        weapon.type.toString(),
                        weapon.category.toString(),
                        weapon.power,
                        weapon.weaponTraits,
                        weapon.description,
                        weapon.price,
                        weapon.weight,
                        weapon.availability.toString(),
                        weapon.url,
                        weapon.range,
                        weapon.reloadTime,
                        weapon.id
                )
            } else {
                throw InvalidEntityException(ErrorCode.WEAPON_EMPTY_ID)
            }
        }
    }
}