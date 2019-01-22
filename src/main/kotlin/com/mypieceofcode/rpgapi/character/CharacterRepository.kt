package com.mypieceofcode.rpgapi.character

import org.springframework.data.repository.CrudRepository

interface CharacterRepository : CrudRepository<DbCharacter, String> {
}