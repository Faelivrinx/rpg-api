package com.mypieceofcode.rpgapi.domain.enums

enum class Trait {
    WW, US, K, ODP, ZR, INT, SW, OGD, A, ZYW, S, WT, SZ, MAG, PO, PP, OTHER, INVALID;

    companion object {
        fun createTrait(trait: String) : Trait{
            return try {
                Trait.valueOf(trait)
            } catch (e: IllegalArgumentException){
                Trait.INVALID
            }
        }
    }
}