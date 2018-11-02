package com.mypieceofcode.rpgapi.domain.enums

import java.lang.IllegalArgumentException

enum class Availability {
    RARE, LITTLE, AVERAGE, HIGH, INVALID;

    companion object {
        fun createAvailability(name: String) : Availability{
            return try {
                Availability.valueOf(name.toUpperCase())
            } catch (e: IllegalArgumentException){
                Availability.INVALID
            }
        }
    }
}