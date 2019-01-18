package com.mypieceofcode.rpgapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(errorCode: ErrorCode)
    :RuntimeException(errorCode.toString())