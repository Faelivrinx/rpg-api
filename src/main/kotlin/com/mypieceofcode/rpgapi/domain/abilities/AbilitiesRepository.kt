package com.mypieceofcode.rpgapi.domain.abilities

import com.mypieceofcode.rpgapi.domain.Repository
import java.util.*


interface AbilitiesRepository : Repository<Ability> {
    override fun findAll(): List<Ability>
    override fun findByName(name: String): Ability?
    override fun findById(id: String): Optional<Ability>
    override fun create(ability: Ability)
    override fun update(ability: Ability)
    override fun deleteById(id: String)
    override fun existsByName(name: String): Boolean
    override fun existsById(id: String): Boolean
}