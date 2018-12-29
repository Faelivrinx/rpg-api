package com.mypieceofcode.rpgapi.domain.enums

import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidParameterException


enum class Sex {
    MALE, FEMALE, OTHER;

    companion object {
        fun createSex(name: String): Sex {
            return try {
                Sex.valueOf(name)
            } catch (e: IllegalArgumentException) {
                OTHER
            }
        }

        fun createDb(sex: Sex) : Int {
            return when(sex) {
                MALE -> 0
                FEMALE -> 1
                OTHER -> 2
            }
        }

        fun fromDb(value: Int) : Sex {
            return when(value){
                0 -> MALE
                1 -> FEMALE
                2 -> OTHER
                else -> throw InvalidParameterException(ErrorCode.CREATURE_INVALID_SEX)
            }
        }
    }


}