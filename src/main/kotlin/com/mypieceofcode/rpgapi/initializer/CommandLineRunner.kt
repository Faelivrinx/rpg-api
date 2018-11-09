package com.mypieceofcode.rpgapi.initializer

import com.mypieceofcode.rpgapi.infrastructure.persistence.DbProfession
import com.mypieceofcode.rpgapi.infrastructure.professions.DbProfessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class CommandLineRunner : CommandLineRunner {

    @Autowired
    lateinit var repository: DbProfessionRepository

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    override fun run(vararg args: String?) {

    }

}