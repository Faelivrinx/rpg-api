package com.mypieceofcode.rpgapi.infrastructure.persistence

import com.mypieceofcode.rpgapi.character.DbCharacter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class DbUser(

        @DBRef
        val character: DbCharacter,

        val username: String,
        @Id val id: String? = null
) {
}