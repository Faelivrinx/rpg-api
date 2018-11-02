package com.mypieceofcode.rpgapi.domain.enums

enum class WeaponCategory {
    BASIC, TWO_HANDED, CAVALERY, FLAIL, PARRY, FENCING, IMMOBILISATION,
    LONG_BOW, FIREARM, CROSSBOW, THROWN, MECHANIC, SLING, OTHER;

    companion object {
        fun createWeaponCategory(category: String) : WeaponCategory{
            return try {
                WeaponCategory.valueOf(category.toUpperCase())
            } catch (e: IllegalArgumentException){
                WeaponCategory.OTHER
            }
        }
    }
}