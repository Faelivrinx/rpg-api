package com.mypieceofcode.rpgapi.application.mutations

import com.mypieceofcode.rpgapi.application.mutations.dto.CreateMutationDto
import com.mypieceofcode.rpgapi.application.mutations.dto.MutationDto
import com.mypieceofcode.rpgapi.application.mutations.dto.UpdateMutationDto
import com.mypieceofcode.rpgapi.domain.mutations.MutationFactory
import com.mypieceofcode.rpgapi.domain.mutations.MutationRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Service

@Service
class MutationService(
        val repository: MutationRepository,
        val factory: MutationFactory

) {
    fun getMutations(): List<MutationDto> = repository.findAll().map { MutationDto.from(it) }

    fun createMutation(dto: CreateMutationDto) {
        when (repository.existsByName(dto.name)) {
            true -> {
                throw EntityAlreadyExistsException(ErrorCode.MUTATION_ALREADY_EXISTS)
            }
            else -> repository.create(factory.createMutation(dto))
        }
    }

    fun getMutationById(id: String): MutationDto {
        val mutation = repository.findById(id)
        return if (mutation.isPresent) {
            MutationDto.from(mutation.get())
        } else {
            throw MissingEntityException(ErrorCode.MUTATION_NOT_FOUND)
        }
    }

    fun updateMutation(dto: UpdateMutationDto) {
        val mutation = repository.findById(dto.id)
        if (mutation.isPresent) {
            repository.update(factory.createMutation(dto))
        } else {
            throw MissingEntityException(ErrorCode.MUTATION_NOT_FOUND)
        }
    }

    fun deleteMutation(id: String) {
        when (repository.existsById(id)) {
            true -> {
                repository.deleteById(id)
            }
            else -> {
                throw MissingEntityException(ErrorCode.MUTATION_NOT_FOUND)
            }
        }
    }
}