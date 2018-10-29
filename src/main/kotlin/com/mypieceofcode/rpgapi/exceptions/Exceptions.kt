package com.mypieceofcode.rpgapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
internal class InvalidParameterException(errorCode: ErrorCode)
    : RuntimeException(errorCode.toString())

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
internal class EntityException(errorCode: ErrorCode)
    : RuntimeException(errorCode.toString())

@ResponseStatus(HttpStatus.NOT_FOUND)
internal class MissingEntityException(errorCode: ErrorCode)
    :RuntimeException(errorCode.toString())