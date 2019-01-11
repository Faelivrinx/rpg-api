package com.mypieceofcode.rpgapi.domain.mutations.factory

import com.mypieceofcode.rpgapi.application.mutations.dto.CreateMutationDto
import com.mypieceofcode.rpgapi.application.mutations.dto.UpdateMutationDto
import com.mypieceofcode.rpgapi.domain.mutations.*
import com.mypieceofcode.rpgapi.exceptions.DomainException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.InvalidParameterException
import org.springframework.stereotype.Service

@Service
class DefaultMutationFactory : MutationFactory {
    override fun createMutation(dto: Any): Mutation {
        return when(dto){
            is CreateMutationDto -> createNewMutation(dto)
            is UpdateMutationDto -> updateMutation(dto)
            else -> throw DomainException(ErrorCode.MUTATION_UNSUPPORTED_FACTORY)

        }
    }

    private fun updateMutation(dto: UpdateMutationDto): Mutation {
        val table = dto.table
        return Mutation(dto.name,
                MutationType.createType(dto.type),
                dto.description,
                Gods.createGod(dto.godType),
                dto.ps,
                dto.roll,
                dto.comment,
                dto.variants,
                createTable(table),
                dto.id
        )
    }

    private fun createNewMutation(dto: CreateMutationDto) : Mutation {
        val table = dto.table ?: emptyList()
        return Mutation(dto.name,
                MutationType.createType(dto.type),
                dto.description,
                Gods.createGod(dto.godType),
                dto.ps,
                dto.roll,
                dto.comment,
                dto.variants,
                createTable(table)
        )
    }

    private fun createTable(table: List<Pair<String, List<String>>>) : MutationTable
    {
        return if(!table.isEmpty()){
            val data = table.map { it.second }
            if(data.map { it.size }.distinct().size > 1){
                throw InvalidParameterException(ErrorCode.MUTATION_INVALID_TABLE_LENGTH)
            }
            MutationTable(table.toMap(), data.first().size)

        } else {
            MutationTable.createEmpty()
        }
    }
}