package com.mypieceofcode.rpgapi.initializer

import com.mypieceofcode.rpgapi.domain.enums.Trait
import com.mypieceofcode.rpgapi.domain.profession.TraitWithValue
import com.mypieceofcode.rpgapi.infrastructure.abilities.DbAbilitiesRepository
import com.mypieceofcode.rpgapi.infrastructure.armors.DbArmorRepository
import com.mypieceofcode.rpgapi.infrastructure.creatures.DbCreatureRepository
import com.mypieceofcode.rpgapi.infrastructure.items.DbItemRepository
import com.mypieceofcode.rpgapi.infrastructure.persistence.*
import com.mypieceofcode.rpgapi.infrastructure.professions.DbProfessionRepository
import com.mypieceofcode.rpgapi.infrastructure.skills.DbSkillRepository
import com.mypieceofcode.rpgapi.infrastructure.weapons.DbWeaponRepository
import com.sun.org.apache.xpath.internal.operations.Bool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class CommandLineRunner : CommandLineRunner {

    @Value("\${update_db}")
    val updateEmptyDb: Boolean? = null

    @Autowired
    lateinit var creatureRepo: DbCreatureRepository

    @Autowired
    lateinit var skillRepo: DbSkillRepository

    @Autowired
    lateinit var abilityRepo: DbAbilitiesRepository

    @Autowired
    lateinit var itemRepo: DbItemRepository

    @Autowired
    lateinit var weaponRepo: DbWeaponRepository

    @Autowired
    lateinit var armorRepo: DbArmorRepository

    @Autowired
    lateinit var professionRepo: DbProfessionRepository

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    override fun run(vararg args: String?) {

        if (updateEmptyDb != null && updateEmptyDb == true) {
            for (collectionName in mongoTemplate.collectionNames) {
                mongoTemplate.dropCollection(collectionName)
            }

            //Creating
            val skill = DbSkills("Jakis skill", "Fajny opis skilla", "WW", "BASIC")
            val skill1 = DbSkills("Skill jak nic", "Mozna, czemu nie", "US", "ADVANCE")

            val ability = DbAbility("Umiejetnosc", "Jeszcze lepsza umiejetnosc")
            val ability1 = DbAbility("Nieprzydanta umiejetnosc", "Czolganie na stojaco")

            val item = DbItem("Fajny przedmiot", "Mozna z nim nic nei robic", 23f, "RARE")
            val item1 = DbItem("Mieszek zlota", "Mozna go np. ukrasc", 1000f, "No rzadki dosc")

            val weapon = DbWeapon("Krotki miecz", "WHITE", "BASIC", "S+1", listOf("obuchowa"), "Fajny miecz, mozna machac", 100f, 10f, "RARE", "")
            val weapon1 = DbWeapon("Elfi miotacz", "RANGED", "ADVANCE", "S+10", listOf("przebijajaca"), "Fajny luk jak dla szyszkojada", 100f, 10f, "W lesie sie trafi", "", 20f, "Akcja")

            val armor = DbArmor("Kolcza kolczuga", "CHAIN", listOf("HEAD"), 3, 100f, 20f, "RARE", "Fajni ludzie i armor", "")

            val profession = DbProfession("Magik czarodziej", skills = mutableListOf(skill), abilities = mutableListOf(ability))

            val creature = DbCreature("Potwor z bagien", "Nie czlowiek", profession, 1000, 1, mutableListOf(TraitWithValue(Trait.WW, 1)), mutableListOf(skill), mutableListOf(ability), weapons = mutableListOf(weapon, weapon1), armors = mutableListOf(armor))


            //Saving
            skillRepo.save(skill)
            skillRepo.save(skill1)

            abilityRepo.save(ability)
            abilityRepo.save(ability1)

            itemRepo.save(item)
            itemRepo.save(item1)

            weaponRepo.save(weapon)
            weaponRepo.save(weapon1)

            armorRepo.save(armor)

            professionRepo.save(profession)

            creatureRepo.save(creature)
        }
    }

}