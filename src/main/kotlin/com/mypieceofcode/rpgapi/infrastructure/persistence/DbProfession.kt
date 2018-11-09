package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.domain.profession.BasicProfession
import com.mypieceofcode.rpgapi.domain.profession.OptionalSkill
import com.mypieceofcode.rpgapi.domain.profession.Profession
import com.mypieceofcode.rpgapi.domain.profession.ProfessionTrait
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.security.InvalidParameterException

@Document(collection = "profession")
data class DbProfession(

        val name: String,
        val description: String = "",

        @DBRef
        val skills: MutableList<DbSkills> = mutableListOf(),

        @DBRef
        val optionalSkills: MutableList<Pair<DbSkills, DbSkills>> = mutableListOf(),

        @DBRef
        val abilities: MutableList<DbAbility> = mutableListOf(),

        @DBRef
        val items: MutableList<DbItem> = mutableListOf(),

        @DBRef
        val inProfession: MutableList<DbProfession> = mutableListOf(),

        @DBRef
        val outProfession: MutableList<DbProfession> = mutableListOf(),

        val traits: MutableList<ProfessionTrait> = mutableListOf(),

        val url: String = "",

        val id: String? = null
) {

        companion object {
            fun toProfession(db: DbProfession) : Profession = Profession(db.name, db.description,
                    db.skills.map { DbSkills.toSkill(it) },
                    db.optionalSkills.map { OptionalSkill(DbSkills.toSkill(it.first), DbSkills.toSkill(it.second)) },
                    db.abilities.map { DbAbility.toAbility(it) },
                    db.items.map { DbItem.toItem(it) },
                    db.inProfession.map { toBasicProfession(it) },
                    db.outProfession.map { toBasicProfession(it) },
                    db.traits,
                    db.url,
                    db.id)

                fun toBasicProfession(db: DbProfession) : BasicProfession {
                        if (db.id != null){
                                BasicProfession(db.name, db.id)
                        }
                        throw com.mypieceofcode.rpgapi.exceptions.InvalidParameterException(ErrorCode.PROFESSION_EMPTY_ID)
                }
        }
}