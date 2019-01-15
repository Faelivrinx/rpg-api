package com.mypieceofcode.rpgapi.domain.creatures

enum class CreatureType {
    MUTANT, ANIMAL, UNDEAD, DEMON, OTHER;

    companion object {
        fun createCreature(name: String) : CreatureType{
           return try {
               CreatureType.valueOf(name)
           } catch (e: IllegalArgumentException){
               OTHER
           }
        }
    }
}