package com.mypieceofcode.rpgapi.infrastructure.mutations

import com.mypieceofcode.rpgapi.domain.mutations.Mutation
import com.mypieceofcode.rpgapi.domain.mutations.MutationRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbMutation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MutationRepository(
        val repository: DbMutationRepository
) : MutationRepository {

    override fun findAll(): List<Mutation> = repository.findAll().map { DbMutation.from(it) }

    override fun findByName(name: String): Mutation? {
        val mutation = repository.findByName(name) ?: throw MissingEntityException(ErrorCode.MUTATION_NOT_FOUND)
        return DbMutation.from(mutation)
    }

    override fun findById(id: String): Optional<Mutation> {
        return repository.findById(id).map { DbMutation.from(it) }
    }

    override fun create(obj: Mutation) {
        if (repository.existsByName(obj.name)){
            throw EntityAlreadyExistsException(ErrorCode.MUTATION_ALREADY_EXISTS)
        }

        repository.save(DbMutation.to(obj))
    }

    override fun update(obj: Mutation) {
        if(obj.id != null && repository.existsById(obj.id)){
            val spell = DbMutation.to(obj)
            repository.save(spell)
        }
        throw MissingEntityException(ErrorCode.MUTATION_NOT_FOUND)
    }

    override fun deleteById(id: String) {
        if (repository.existsById(id)){
            repository.deleteById(id)
        } else {
            throw MissingEntityException(ErrorCode.MUTATION_NOT_FOUND)
        }
    }

    override fun existsByName(name: String): Boolean = repository.existsByName(name)
    override fun existsById(id: String): Boolean = repository.existsById(id)
}

@Repository
interface DbMutationRepository : MongoRepository<DbMutation, String> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): DbMutation?
}