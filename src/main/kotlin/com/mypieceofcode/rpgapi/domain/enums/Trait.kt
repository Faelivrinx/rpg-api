package com.mypieceofcode.rpgapi.domain.enums

import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode

enum class Trait {
    WW, US, K, ODP, ZR, INT, SW, OGD, A, ZYW, S, WT, SZ, MAG, PO, PP;

    companion object {
        fun createTrait(trait: String) : Trait{
            return try {
                Trait.valueOf(trait)
            } catch (e: IllegalArgumentException){
                throw DomainException(ErrorCode.INVALID_TRAIT)
            }
        }

         fun getProgressStep(name: Trait): Int {
            when(name){
                Trait.WW -> return 5
                Trait.A -> return 1
                Trait.INT -> return 5
                Trait.K -> return 5
                Trait.MAG -> return 1
                Trait.ODP -> return 5
                Trait.PO -> return 1
                Trait.PP -> return 1
                Trait.OGD -> return 5
                Trait.S -> return 1
                Trait.SW -> return 5
                Trait.SZ-> return 1
                Trait.US -> return 5
                Trait.WT -> return 1
                Trait.ZR -> return 5
                Trait.ZYW -> return 1
                else -> throw DomainException(ErrorCode.INVALID_TRAIT)
            }

        }
    }
}