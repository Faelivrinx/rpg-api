package com.mypieceofcode.rpgapi.domain.enums

import java.lang.IllegalArgumentException

enum class ProtectionArea {
    HEAD, BODY, LEFT_HAND, RIGHT_HAND, LEFT_LEG, RIGHT_LEG, INVALID;

    companion object {
        fun createArea(name: String) : ProtectionArea {
            return try {
                ProtectionArea.valueOf(name.toUpperCase())
            } catch (e : IllegalArgumentException){
                ProtectionArea.INVALID
            }
        }
    }
}