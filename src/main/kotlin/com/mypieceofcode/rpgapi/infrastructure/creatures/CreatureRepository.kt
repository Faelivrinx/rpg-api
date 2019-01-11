package com.mypieceofcode.rpgapi.infrastructure.creatures

import com.mypieceofcode.rpgapi.domain.creatures.Creature
import com.mypieceofcode.rpgapi.domain.creatures.CreatureRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import com.mypieceofcode.rpgapi.infrastructure.persistence.*
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreatureRepository(
        val repository: DbCreatureRepository
) : CreatureRepository {

    override fun findAll(): List<Creature> = repository.findAll().map { DbCreature.fromDb(it) }

    override fun findByName(name: String): Creature? {
        val creature = repository.findByName(name) ?: throw MissingEntityException(ErrorCode.CREATURE_NOT_FOUND)
        return DbCreature.fromDb(creature)
    }

    override fun findById(id: String): Optional<Creature> {
        return repository.findById(id).map { DbCreature.fromDb(it) }
    }

    override fun create(obj: Creature) {
        if (repository.existsByName(obj.name)) {
            throw EntityAlreadyExistsException(ErrorCode.CREATURE_ALREADY_EXISTS)
        }



        val db = DbCreature(obj.name, obj.type, obj.traits,
                obj.skills.map { DbSkills.fromSkill(it) }.toMutableList(),
                obj.abilities.map { DbAbility.fromAbility(it) }.toMutableList(),
                obj.imageName,
                obj.description,
                obj.specialRules,
                obj.armors.map { DbArmor.fromArmor(it) }.toMutableList(),
                obj.weapons.map { DbWeapon.fromWeapon(it) }.toMutableList(),
                obj.items.map { DbItem.fromItem(it) }.toMutableList())

        repository.save(db)
    }

    override fun update(obj: Creature) {
        if (obj.id != null && repository.existsByName(obj.name)) {


            val db = DbCreature(obj.name, obj.type, obj.traits,
                    obj.skills.map { DbSkills.fromSkill(it) }.toMutableList(),
                    obj.abilities.map { DbAbility.fromAbility(it) }.toMutableList(),
                    obj.imageName,
                    obj.description,
                    obj.specialRules,
                    obj.armors.map { DbArmor.fromArmor(it) }.toMutableList(),
                    obj.weapons.map { DbWeapon.fromWeapon(it) }.toMutableList(),
                    obj.items.map { DbItem.fromItem(it) }.toMutableList(),
                    obj.id)
            repository.save(db)

        } else {
            throw MissingEntityException(ErrorCode.CREATURE_NOT_FOUND)
        }
    }

    override fun deleteById(id: String) {
        val creature = repository.findById(id)
        if (creature.isPresent) {
            repository.deleteById(id)
        } else {
            throw MissingEntityException(ErrorCode.CREATURE_NOT_FOUND)
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
interface DbCreatureRepository : MongoRepository<DbCreature, String> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): DbCreature?
}