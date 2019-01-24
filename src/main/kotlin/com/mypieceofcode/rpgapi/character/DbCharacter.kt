package com.mypieceofcode.rpgapi.character;

import com.mypieceofcode.rpgapi.character.model.AdditionalCharacterInfo
import com.mypieceofcode.rpgapi.character.model.CharacterTraits
import com.mypieceofcode.rpgapi.domain.enums.Trait
import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbAbility
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbArmor
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbProfession
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbSkills
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "characters")
data class DbCharacter (
        val name: String,
        val race: String,

        @DBRef
        val currentProfession: DbProfession,

        val traits: MutableList<CharacterTraits>,

        @DBRef
        val previousProfession: DbProfession?,

        @DBRef
        val skills: List<DbSkills>,

        @DBRef
        val abilities: List<DbAbility>,

        @DBRef
        val armors: List<DbArmor>? = null,

        val additionalInfo: List<AdditionalCharacterInfo>?,

        var xp: Long = 0,
        @Id val id: String? = null
) {

        companion object {
            fun randomCharacter(name: String, race: String, profession: DbProfession) : DbCharacter {

                    val traits = Trait.values().map { CharacterTraits(it.name,
                            getFullName(it),
                            profession.traits.find { trait -> trait.name.toString() == it.name }?.baseValue!!,
                            profession.traits.find { trait -> trait.name.toString() == it.name }?.baseValue!!,
                            profession.traits.find { trait -> trait.name.toString() == it.name }?.extendValue!!,
                            Trait.getProgressStep(it)
                            )
                    }

                    return DbCharacter(name,
                            race,
                            profession,
                            traits.toMutableList(),
                            null,
                            emptyList(),
                            emptyList(),
                            null,
                            emptyList(),
                            0)
            }

                private fun getFullName(name: Trait): String {
                        when(name){
                                Trait.WW -> return "Walka wręcz"
                                Trait.A -> return "Ataki"
                                Trait.INT -> return "Inteligencja"
                                Trait.K -> return "Kondycja"
                                Trait.MAG -> return "Magia"
                                Trait.ODP -> return "Odporność"
                                Trait.PO -> return "Punkty obłędu"
                                Trait.PP -> return "Punkty przeznaczenia"
                                Trait.OGD -> return "Ogłada"
                                Trait.S -> return "Siła"
                                Trait.SW -> return "Siła woli"
                                Trait.SZ-> return "Szczęscie"
                                Trait.US -> return "Umiejętności strzeleckie"
                                Trait.WT -> return "Wytrzymałość"
                                Trait.ZR -> return "Zręcznośc"
                                Trait.ZYW -> return "Żywotność"
                                else -> throw DomainException(ErrorCode.CHARACTER_INVALID_TRAIT_TYPE)
                        }

                }
        }

}