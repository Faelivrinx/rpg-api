package com.mypieceofcode.rpgapi.domain.enums

import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode

enum class Availability {
    COMMON, RARE, VERY_RARE, SPORADIC, LITTLE;

    companion object {
        fun createAvailability(name: String) : Availability {
            return try {
                Availability.valueOf(name)
            } catch (e: IllegalArgumentException){
                throw DomainException(ErrorCode.INVALID_AVAILABILITY_VALUE)
            }
        }
    }
}