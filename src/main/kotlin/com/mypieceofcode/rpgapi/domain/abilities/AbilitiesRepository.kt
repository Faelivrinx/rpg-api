package com.mypieceofcode.rpgapi.domain.abilities

import java.util.*


interface AbilitiesRepository {
    fun findAll(): List<Ability>
    fun findByName(name: String): Ability?
    fun findById(id: String): Optional<Ability>
    fun createAbility(ability: Ability)
    fun updateAbility(ability: Ability)
    fun delete(id: String)
    fun exists(name: String): Boolean
    fun existsById(id: String): Boolean
}