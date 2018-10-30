package com.mypieceofcode.rpgapi.infrastructure.skill

import com.mypieceofcode.rpgapi.domain.skills.Skill
import com.mypieceofcode.rpgapi.domain.skills.SkillRepository
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbAbilities
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbSkills
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component
class SkillRepositoryImpl(
        val repository: DbSkillRepository
) : SkillRepository {

    override fun findAll(): List<Skill> {
        return repository.findAll().map { DbSkills.toSkill(it) }
    }

    override fun findByName(name: String): Skill? {
        return repository.findByName(name)?.let { DbSkills.toSkill(it) }
    }

    override fun findById(id: String): Optional<Skill> {
        return repository.findById(id).map { DbSkills.toSkill(it) }
    }

    override fun create(obj: Skill) {
        repository.save(DbSkills.fromSkill(obj))
    }

    override fun update(obj: Skill) {
        repository.save(DbSkills.fromSkill(obj))
    }

    override fun deleteById(id: String) = repository.deleteById(id)

    override fun existsByName(name: String): Boolean = repository.existsByName(name)

    override fun existsById(id: String): Boolean = repository.existsById(id)
}

@Repository
interface DbSkillRepository : MongoRepository<DbSkills, String> {

    fun findByName(name: String): DbSkills?
    fun existsByName(name: String): Boolean
}
