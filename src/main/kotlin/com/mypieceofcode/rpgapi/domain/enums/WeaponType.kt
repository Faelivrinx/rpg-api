package com.mypieceofcode.rpgapi.domain.enums

import java.lang.IllegalArgumentException

enum class WeaponType {
    WHITE, RANGED, AMMO, OTHER;

    companion object {
        fun createWeaponType(type: String) : WeaponType {
            return try {
                WeaponType.valueOf(type)
            } catch (e: IllegalArgumentException){
                WeaponType.OTHER
            }
        }
    }
}