package com.mypieceofcode.rpgapi.domain

import java.util.*

interface Repository<T> {
    fun findAll(): List<T>
    fun findByName(name: String): T?
    fun findById(id: String): Optional<T>
    fun create(obj: T)
    fun update(obj: T)
    fun deleteById(id: String)
    fun existsByName(name: String): Boolean
    fun existsById(id: String): Boolean
}