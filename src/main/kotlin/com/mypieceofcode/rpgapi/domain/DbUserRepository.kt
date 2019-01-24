package com.mypieceofcode.rpgapi.domain

import com.mypieceofcode.rpgapi.infrastructure.persistence.DbUser
import org.springframework.data.repository.CrudRepository

interface DbUserRepository : CrudRepository<DbUser, String> {
    fun findByUsername(username: String) : DbUser?
}