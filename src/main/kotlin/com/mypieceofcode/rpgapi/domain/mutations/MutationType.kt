package com.mypieceofcode.rpgapi.domain.mutations

import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidParameterException

enum class MutationType {
    SINGLE, MULTIPLE;

    companion object {
        fun createType(type: String) : MutationType{
            return try {
                MutationType.valueOf(type)
            } catch (e: IllegalArgumentException){
                throw InvalidParameterException(ErrorCode.MUTATION_INVALID_TYPE)
            }
        }
    }
}