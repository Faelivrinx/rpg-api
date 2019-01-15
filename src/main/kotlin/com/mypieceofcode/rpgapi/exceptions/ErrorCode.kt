package com.mypieceofcode.rpgapi.exceptions

enum class ErrorCode {
    ABILITY_EMPTY_NAME,
    ABILITY_EMPTY_DESCRIPTION,
    ABILITY_EMPTY_ID,
    ABILITY_ALREADY_EXISTS,
    ABILITY_NOT_FOUND,

    SKILL_EMPTY_ID,
    SKILL_EMPTY_NAME,
    SKILL_EMPTY_DESCRIPTION,
    SKILL_INVALID_TRAIT,
    SKILL_INVALID_TYPE,
    SKILL_ALREADY_EXISTS,
    SKILL_NOT_FOUND,

    ITEM_EMPTY_ID,
    ITEM_EMPTY_NAME,
    ITEM_EMPTY_DESCRIPTION,
    ITEM_EMPTY_PRICE,
    ITEM_EMPTY_AVAILABILITY,
    ITEM_INVALID_PRICE,
    ITEM_INVALID_AVAILABILITY,
    ITEM_NOT_FOUND,
    ITEM_ALREADY_EXISTS,

    ARMOR_EMPTY_ID,
    ARMOR_EMPTY_NAME,
    ARMOR_EMPTY_TYPE,
    ARMOR_EMPTY_PROTECTION_AREA,
    ARMOR_EMPTY_PZ,
    ARMOR_INVALID_PROTECTION_AREA,
    ARMOR_INVALID_PRICE,
    ARMOR_INVALID_PZ,
    ARMOR_INVALID_AVAILABILITY,
    ARMOR_ALREADY_EXISTS,
    ARMOR_NOT_FOUND,

    WEAPON_EMPTY_ID,
    WEAPON_EMPTY_NAME,
    WEAPON_EMPTY_TYPE,
    WEAPON_EMPTY_CATEGORY,
    WEAPON_EMPTY_POWER,
    WEAPON_EMPTY_TRAITS,
    WEAPON_EMPTY_RANGE,
    WEAPON_EMPTY_RELOAD_TIME,
    WEAPON_INVALID_PRICE,
    WEAPON_INVALID_AVAILABILITY,
    WEAPON_INVALID_RANGE,
    WEAPON_ALREADY_EXISTS,
    WEAPON_NOT_FOUND,
    WEAPON_CONVERTER_EXCEPTION,

    PROFESSION_EMPTY_NAME,
    PROFESSION_EMPTY_ID,
    PROFESSION_INVALID_IN_PROFESSION,
    PROFESSION_INVALID_OUT_PROFESSION,
    PROFESSION_IN_NOT_FOUND,
    PROFESSION_OUT_NOT_FOUND,
    PROFESSION_NOT_FOUND,
    PROFESSION_ALREADY_EXISTS,
    PROFESSION_CREATION_UNSUPPORTED,

    CREATURE_EMPTY_ID,
    CREATURE_INVALID_SEX,
    CREATURE_NOT_FOUND,
    CREATURE_ALREADY_EXISTS,
    CREATURE_CREATION_UNSUPPORTED,

    SPELL_EMPTY_NAME,
    SPELL_EMPTY_POWER_LEVEL,
    SPELL_EMPTY_CAST_TIME,
    SPELL_EMPTY_COMPONENT,
    SPELL_EMPTY_DESCRIPTION,
    SPELL_INVALID_TABLE,
    SPELL_NOT_FOUND,
    SPELL_ALREADY_EXISTS,

    MUTATION_INVALID_TABLE_LENGTH,
    MUTATION_INVALID_TYPE,
    MUTATION_EMPTY_NAME,
    MUTATION_EMPTY_TYPE,
    MUTATION_EMPTY_DESCRIPTION,
    MUTATION_EMPTY_GOD_TYPE,
    MUTATION_NOT_FOUND,
    MUTATION_ALREADY_EXISTS,
    MUTATION_UNSUPPORTED_FACTORY,


    INVALID_AVAILABILITY_VALUE
}