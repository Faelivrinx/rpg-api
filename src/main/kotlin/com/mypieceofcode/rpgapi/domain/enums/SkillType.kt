package com.mypieceofcode.rpgapi.domain.enums

import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode

enum class SkillType {
    BASIC, ADVANCE, INVALID;

    companion object {
        fun createSkillType(type: String) : SkillType{
            return try {
                SkillType.valueOf(type)
            } catch (e: IllegalArgumentException){
                SkillType.INVALID
            }
        }
    }
}