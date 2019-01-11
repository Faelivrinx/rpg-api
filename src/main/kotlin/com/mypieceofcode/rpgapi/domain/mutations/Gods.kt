package com.mypieceofcode.rpgapi.domain.mutations

import java.lang.IllegalArgumentException

enum class Gods {
    KHORN, NURGLE, SLAANESH, TZEENTCH, OTHER;

    companion object {
        fun createGod(name: String): Gods{
            return try {
                Gods.valueOf(name.toUpperCase())
            } catch (e: IllegalArgumentException){
                OTHER
            }
        }
    }
}