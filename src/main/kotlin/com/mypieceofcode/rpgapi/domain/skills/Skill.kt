package com.mypieceofcode.rpgapi.domain.skills

import com.mypieceofcode.rpgapi.domain.DomainObject
import com.mypieceofcode.rpgapi.domain.enums.SkillType
import com.mypieceofcode.rpgapi.domain.enums.Trait
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidEntityException
import org.apache.commons.lang3.StringUtils.isBlank

data class Skill(
        val name: String,
        val description: String,
        val trait: Trait = Trait.OTHER,
        val type: SkillType = SkillType.BASIC,
        val id: String? = null
) : DomainObject() {

    init {
        validate()
    }

    override fun validate() {
        if (isBlank(this.name)){
            throw InvalidEntityException(ErrorCode.SKILL_EMPTY_NAME)
        }
        if (isBlank(this.description)){
            throw InvalidEntityException(ErrorCode.SKILL_EMPTY_DESCRIPTION)
        }
    }
}