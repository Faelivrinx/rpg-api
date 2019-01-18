package com.mypieceofcode.rpgapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UnauthorizeException(errorCode: ErrorCode)
    :RuntimeException(errorCode.toString())