package com.mypieceofcode.rpgapi.domain.abilities

import com.mypieceofcode.rpgapi.domain.DomainObject
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException
import com.mypieceofcode.rpgapi.exceptions.InvalidParameterException
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.StringUtils.isBlank
import java.util.*

data class Ability(
        val name: String,
        val description: String,
        val id: String = UUID.randomUUID().toString()
): DomainObject(){
    init {
        validate()
    }

    override fun validate() {
        if (isBlank(this.name)){
            throw InvalidEntityException(ErrorCode.ABILITY_EMPTY_NAME)
        }
        if (isBlank(this.description)){
            throw InvalidEntityException(ErrorCode.ABILITY_EMPTY_DESCRIPTION)
        }
        if (isBlank(this.id)){
            throw InvalidEntityException(ErrorCode.ABILITY_EMPTY_ID)
        }

    }

}