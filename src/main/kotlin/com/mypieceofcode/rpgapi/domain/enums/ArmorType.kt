package com.mypieceofcode.rpgapi.domain.enums

enum class ArmorType {
    LEATHER, CHAIN, PLATE, GRIMIL, STUDDED, SCALE, OTHER;

    companion object {
        fun createArmorType(type: String) : ArmorType {
            return try {
                ArmorType.valueOf(type.toUpperCase())
            } catch (e: IllegalArgumentException){
                ArmorType.OTHER
            }
        }
    }
}