package com.mypieceofcode.rpgapi.domain.profession

import com.mypieceofcode.rpgapi.domain.skills.Skill

data class OptionalSkill(
        val first: Skill,
        val second: Skill
) {

}