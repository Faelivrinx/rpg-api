package com.mypieceofcode.rpgapi.application.skills

import com.mypieceofcode.rpgapi.application.skills.dto.NewSkillDto
import com.mypieceofcode.rpgapi.application.skills.dto.SkillDto
import com.mypieceofcode.rpgapi.application.skills.dto.UpdateSkillDto
import com.mypieceofcode.rpgapi.domain.skills.SkillRepository
import com.mypieceofcode.rpgapi.exceptions.EntityAlreadyExistsException
import com.mypieceofcode.rpgapi.exceptions.ErrorCode
import com.mypieceofcode.rpgapi.exceptions.MissingEntityException
import org.springframework.stereotype.Service

@Service
class SkillService(
        val repository: SkillRepository
) {

    fun getSkills(): List<SkillDto> = repository.findAll().map { SkillDto.mapToDto(it) }

    fun createSkill(dto: NewSkillDto) {
        when (repository.existsByName(dto.name)) {
            true -> {
                throw EntityAlreadyExistsException(ErrorCode.SKILL_ALREADY_EXISTS)
            }
            else -> repository.create(NewSkillDto.mapToSkill(dto))
        }
    }

    fun getSkillById(id: String): SkillDto {
        val skill = repository.findById(id)
        return if (skill.isPresent) {
            SkillDto.mapToDto(skill.get())
        } else {
            throw MissingEntityException(ErrorCode.SKILL_NOT_FOUND)
        }
    }

    fun updateSkill(dto: UpdateSkillDto) {
        val skill = repository.findById(dto.id)
        if (skill.isPresent) {
            repository.update(UpdateSkillDto.mapToSkill(dto))
        } else {
            throw MissingEntityException(ErrorCode.SKILL_NOT_FOUND)
        }
    }

    fun deleteSkill(id: String) {
        when (repository.existsById(id)) {
            true -> {
                repository.deleteById(id)
            }
            else -> {
                throw MissingEntityException(ErrorCode.SKILL_NOT_FOUND)
            }
        }
    }
}