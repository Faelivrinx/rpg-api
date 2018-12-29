package com.mypieceofcode.rpgapi.infrastructure.spells

import com.mypieceofcode.rpgapi.domain.spells.Spell
import com.mypieceofcode.rpgapi.domain.spells.SpellRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbSpell
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpellRepository(
       val repository: DbSpellRepository
): SpellRepository {
    override fun findAll(): List<Spell>  = repository.findAll().map { DbSpell.from(it) }

    override fun findByName(name: String): Spell? {
        val spell = repository.findByName(name) ?: throw MissingEntityException(ErrorCode.SPELL_NOT_FOUND)
        return DbSpell.from(spell)
    }

    override fun findById(id: String): Optional<Spell> {
        return repository.findById(id).map { DbSpell.from(it) }
    }

    override fun create(obj: Spell) {
        if (repository.existsByName(obj.name)){
            throw EntityAlreadyExistsException(ErrorCode.SPELL_ALREADY_EXISTS)
        }

        repository.save(DbSpell.to(obj))
    }

    override fun update(obj: Spell) {
        if(obj.id != null && repository.existsById(obj.id)){
            val spell = DbSpell.to(obj)
            repository.save(spell)
        }
        throw MissingEntityException(ErrorCode.SPELL_NOT_FOUND)
    }

    override fun deleteById(id: String) {
        if (repository.existsById(id)){
            repository.deleteById(id)
        } else {
            throw MissingEntityException(ErrorCode.SPELL_NOT_FOUND)
        }

    }

    override fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    override fun existsById(id: String): Boolean {
        return repository.existsById(id)
    }
}

@Repository
interface DbSpellRepository : MongoRepository<DbSpell, String> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): DbSpell?
}