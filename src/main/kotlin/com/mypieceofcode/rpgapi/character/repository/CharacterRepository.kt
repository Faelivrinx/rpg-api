package com.mypieceofcode.rpgapi.character.repository

import com.mypieceofcode.rpgapi.character.DbCharacter
import org.springframework.data.repository.CrudRepository

interface CharacterRepository : CrudRepository<DbCharacter, String> {
}