package com.mypieceofcode.rpgapi.infrastructure.abilities

import com.mypieceofcode.rpgapi.domain.items.Item
import com.mypieceofcode.rpgapi.domain.items.ItemRepository
import com.mypieceofcode.rpgapi.infrastructure.persistence.DbItem
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component
class ItemRepositoryImpl(
        private val repository: DbItemRepository
) : ItemRepository {

    override fun existsById(id: String): Boolean {
        return repository.existsById(id)
    }

    override fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    override fun findAll(): List<Item> {
        val dbAbilities = repository.findAll()
        return dbAbilities.map { DbItem.toItem(it) }
    }

    override fun findByName(name: String): Item? {
        val dbAbility = repository.findByName(name)
        return dbAbility?.let { DbItem.toItem(it) }
    }

    override fun findById(id: String): Optional<Item> {
        return repository.findById(id)
                .map { DbItem.toItem(it) }
    }

    override fun create(item: Item) {
        val fromAbility = DbItem.fromItem(item)
        repository.save(fromAbility)
    }

    override fun update(item: Item) {
        repository.save(DbItem.fromItem(item))
    }

    override fun deleteById(id: String) {
        repository.deleteById(id)
    }

}

@Repository
interface DbItemRepository : MongoRepository<DbItem, String> {

    fun findByName(name: String): DbItem?
    fun existsByName(name: String): Boolean
}
