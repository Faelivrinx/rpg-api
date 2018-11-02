package com.mypieceofcode.rpgapi.domain.equipment.weapons


interface WeaponFactory {
    fun <T> createWeapon(weapon: T) : Weapon
}