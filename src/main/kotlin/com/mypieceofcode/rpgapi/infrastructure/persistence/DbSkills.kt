package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.enums.SkillType
import com.mypieceofcode.rpgapi.domain.enums.Trait
import com.mypieceofcode.rpgapi.domain.skills.Skill
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class DbSkills(
        val name: String,
        val description: String,
        val trait: String,
        val type: String,
        @Id var id: String? = null
) {

    companion object {
        fun toSkill(db: DbSkills) = Skill(db.name, db.description, Trait.createTrait(db.trait), SkillType.createSkillType(db.type), db.id)
        fun fromSkill(skill: Skill) = DbSkills(skill.name, skill.description, skill.trait.toString(), skill.type.toString(), skill.id)
    }

}