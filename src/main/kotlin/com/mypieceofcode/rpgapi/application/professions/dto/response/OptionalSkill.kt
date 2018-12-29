package com.mypieceofcode.rpgapi.application.professions.dto.response

import com.mypieceofcode.rpgapi.application.skills.dto.SkillDto

data class OptionalSkill(
        val first: SkillDto,
        val second: SkillDto
)