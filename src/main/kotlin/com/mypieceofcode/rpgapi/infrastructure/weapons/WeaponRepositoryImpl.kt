package com.mypieceofcode.rpgapi.infrastructure.weapons

import com.mypieceofcode.rpgapi.domain.equipment.weapons.Weapon
import com.mypieceofcode.rpgapi.domain.equipment.weapons.WeaponFactory
import com.mypieceofcode.rpgapi.domain.equipment.weapons.WeaponRepository
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbWeapon
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WeaponRepositoryImpl(
        val repository: DbWeaponRepository,
        val factory: WeaponFactory
) : WeaponRepository {

    override fun existsById(id: String): Boolean {
        return repository.existsById(id)
    }

    override fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    override fun findByName(name: String): Weapon? {
        val dbAbility = repository.findByName(name)
        return dbAbility?.let { factory.createWeapon(it) }
    }

    override fun findById(id: String): Optional<Weapon> {
        return repository.findById(id)
                .map { factory.createWeapon(it) }
    }

    override fun create(weapon: Weapon) {
        repository.save(DbWeapon.fromWeapon(weapon))
    }

    override fun update(weapon: Weapon) {
        repository.save(DbWeapon.fromWeapon(weapon))
    }

    override fun deleteById(id: String) {
        repository.deleteById(id)
    }

    override fun findAll(): List<Weapon> {
       return repository.findAll().map { factory.createWeapon(it) }
    }
}

@Repository
interface DbWeaponRepository : MongoRepository<DbWeapon, String> {
    fun findByName(name: String): DbWeapon?
    fun existsByName(name: String): Boolean
}