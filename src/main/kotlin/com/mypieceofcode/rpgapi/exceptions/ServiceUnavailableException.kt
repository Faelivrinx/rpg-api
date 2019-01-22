package com.mypieceofcode.rpgapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
class ServiceUnavailableException(errorCode: ErrorCode)
    :RuntimeException(errorCode.toString())