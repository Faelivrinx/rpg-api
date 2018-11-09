package com.mypieceofcode.rpgapi.infrastructure.professions

import com.mypieceofcode.rpgapi.domain.profession.Profession
import com.mypieceofcode.rpgapi.domain.profession.ProfessionRepository
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbAbility
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbItem
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbProfession
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbSkills
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
data class ProfessionRepositoryImpl(
        val professionRepository: DbProfessionRepository
) : ProfessionRepository {

    override fun findAll(): List<Profession> {
        return professionRepository.findAll().map { DbProfession.toProfession(it) }
    }

    override fun findByName(name: String): Profession? {
        val profession = professionRepository.existsByName(name)  ?: throw MissingEntityException(ErrorCode.PROFESSION_NOT_FOUND)
        return DbProfession.toProfession(profession)
    }

    override fun findById(id: String): Optional<Profession> {
        return professionRepository.findById(id).map { DbProfession.toProfession(it) }
    }

    override fun create(obj: Profession) {
        val inProfessions = obj.inProfession.map { professionRepository.findById(it.id) }.toCollection(ArrayList())
        val outProfessions = obj.outProfession.map { professionRepository.findById(it.id) }.toCollection(ArrayList())

        val db = DbProfession(
                obj.name,
                obj.description,
                obj.skills.map { DbSkills.fromSkill(it) }.toMutableList(),
                obj.optionalSkills.map { Pair(DbSkills.fromSkill(it.first), DbSkills.fromSkill(it.second)) }.toMutableList(),
                obj.abilities.map { DbAbility.fromAbility(it) }.toMutableList(),
                obj.items.map { DbItem.fromItem(it) }.toMutableList(),
                inProfessions.map { if (it.isPresent) it.get() else throw MissingEntityException(ErrorCode.PROFESSION_IN_NOT_FOUND) }.toCollection(ArrayList()),
                outProfessions.map { if (it.isPresent) it.get() else throw MissingEntityException(ErrorCode.PROFESSION_OUT_NOT_FOUND) }.toCollection(ArrayList()),
                obj.traits.toMutableList(),
                obj.url
        )

        professionRepository.save(db)
    }

    override fun update(obj: Profession) {
        if (obj.id != null && professionRepository.existsById(obj.id)){
            val inProfessions = obj.inProfession.map { professionRepository.findById(it.id) }.toCollection(ArrayList())
            val outProfessions = obj.outProfession.map { professionRepository.findById(it.id) }.toCollection(ArrayList())

            val db = DbProfession(
                    obj.name,
                    obj.description,
                    obj.skills.map { DbSkills.fromSkill(it) }.toMutableList(),
                    obj.optionalSkills.map { Pair(DbSkills.fromSkill(it.first), DbSkills.fromSkill(it.second)) }.toMutableList(),
                    obj.abilities.map { DbAbility.fromAbility(it) }.toMutableList(),
                    obj.items.map { DbItem.fromItem(it) }.toMutableList(),
                    inProfessions.map { if (it.isPresent) it.get() else throw MissingEntityException(ErrorCode.PROFESSION_IN_NOT_FOUND) }.toCollection(ArrayList()),
                    outProfessions.map { if (it.isPresent) it.get() else throw MissingEntityException(ErrorCode.PROFESSION_OUT_NOT_FOUND) }.toCollection(ArrayList()),
                    obj.traits.toMutableList(),
                    obj.url,
                    obj.id
            )
            professionRepository.save(db)
        }
        throw MissingEntityException(ErrorCode.PROFESSION_NOT_FOUND)
    }

    override fun deleteById(id: String) {
        professionRepository.deleteById(id)
    }

    override fun existsByName(name: String): Boolean {
        TODO()
    }

    override fun existsById(id: String): Boolean {
        return professionRepository.existsById(id)
    }
}

@Repository
interface DbProfessionRepository : MongoRepository<DbProfession, String> {
    fun existsByName(name: String): DbProfession?
}