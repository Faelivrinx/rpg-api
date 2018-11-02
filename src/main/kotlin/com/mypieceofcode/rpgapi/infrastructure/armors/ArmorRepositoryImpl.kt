package com.mypieceofcode.rpgapi.infrastructure.armors

import com.mypieceofcode.rpgapi.domain.equipment.armors.Armor
import com.mypieceofcode.rpgapi.domain.equipment.armors.ArmorRepository
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbArmor
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component
class ArmorRepositoryImpl(
        private val repository: DbArmorRepository
) : ArmorRepository {

    override fun existsById(id: String): Boolean {
        return repository.existsById(id)
    }

    override fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    override fun findAll(): List<Armor> {
        val dbAbilities = repository.findAll()
        return dbAbilities.map { DbArmor.toArmor(it) }
    }

    override fun findByName(name: String): Armor? {
        val dbAbility = repository.findByName(name)
        return dbAbility?.let { DbArmor.toArmor(it) }
    }

    override fun findById(id: String): Optional<Armor> {
        return repository.findById(id)
                .map { DbArmor.toArmor(it) }
    }

    override fun create(armor: Armor) {
        val fromAbility = DbArmor.fromArmor(armor)
        repository.save(fromAbility)
    }

    override fun update(armor: Armor) {
        repository.save(DbArmor.fromArmor(armor))
    }

    override fun deleteById(id: String) {
        repository.deleteById(id)
    }

}

@Repository
interface DbArmorRepository : MongoRepository<DbArmor, String> {

    fun findByName(name: String): DbArmor?
    fun existsByName(name: String): Boolean
}
