package com.mypieceofcode.rpgapi.infrastructure.abilities

import com.mypieceofcode.rpgapi.domain.abilities.AbilitiesRepository
import com.mypieceofcode.rpgapi.domain.abilities.Ability
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbAbilities
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

    override fun exists(name: String): Boolean {
        return repository.existsByName(name)
    }

    override fun findAll(): List<Ability> {
        val dbAbilities = repository.findAll()
        return dbAbilities.map { DbAbilities.toAbility(it) }
    }

    override fun findByName(name: String): Ability? {
        val dbAbility = repository.findByName(name)
        return dbAbility?.let { DbAbilities.toAbility(it) }
    }

    override fun findById(id: String): Optional<Ability> {
        return repository.findById(id)
                .map { DbAbilities.toAbility(it) }
    }

    override fun createAbility(ability: Ability) {
        val fromAbility = DbAbilities.fromAbility(ability)
        repository.save(fromAbility)
    }

    override fun updateAbility(ability: Ability) {
        repository.save(DbAbilities.fromAbility(ability))
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

}

@Repository
interface DbAbilitiesRepository : MongoRepository<DbAbilities, String> {

    fun findByName(name: String): DbAbilities?
    fun existsByName(name: String): Boolean
}
