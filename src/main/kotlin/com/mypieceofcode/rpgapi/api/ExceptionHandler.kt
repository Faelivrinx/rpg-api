package com.mypieceofcode.rpgapi.api

import com.mypieceofcode.rpgapi.exceptions.InvalidParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.RuntimeException

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler(){

//    @ExceptionHandler(value = [InvalidParameterException::class])
//    fun invalidArgument(ex: RuntimeException, req: WebRequest): ResponseEntity<String> {
//        return ResponseEntity(ex.message, HttpStatus.UNPROCESSABLE_ENTITY)
//    }
}