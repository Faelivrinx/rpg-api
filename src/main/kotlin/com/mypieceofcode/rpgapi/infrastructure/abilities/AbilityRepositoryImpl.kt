package com.mypieceofcode.rpgapi.infrastructure.abilities

import com.mypieceofcode.rpgapi.domain.abilities.AbilitiesRepository
import com.mypieceofcode.rpgapi.domain.abilities.Ability
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbAbility
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component
class AbilitiesRepositoryImpl(
        private val repository: DbAbilitiesRepository
) : AbilitiesRepository {

    override fun existsById(id: String): Boolean {
        return repository.existsById(id)
    }

    override fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    override fun findAll(): List<Ability> {
        val dbAbilities = repository.findAll()
        return dbAbilities.map { DbAbility.toAbility(it) }
    }

    override fun findByName(name: String): Ability? {
        val dbAbility = repository.findByName(name)
        return dbAbility?.let { DbAbility.toAbility(it) }
    }

    override fun findById(id: String): Optional<Ability> {
        return repository.findById(id)
                .map { DbAbility.toAbility(it) }
    }

    override fun create(ability: Ability) {
        val fromAbility = DbAbility.fromAbility(ability)
        repository.save(fromAbility)
    }

    override fun update(ability: Ability) {
        repository.save(DbAbility.fromAbility(ability))
    }

    override fun deleteById(id: String) {
        repository.deleteById(id)
    }

}

@Repository
interface DbAbilitiesRepository : MongoRepository<DbAbility, String> {

    fun findByName(name: String): DbAbility?
    fun existsByName(name: String): Boolean
}
