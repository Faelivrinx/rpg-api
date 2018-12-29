package com.mypieceofcode.rpgapi.domain.mutations

interface MutationFactory {
    fun createMutation(dto: Any) : Mutation
}