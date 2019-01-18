package com.mypieceofcode.rpgapi.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.client.RestTemplate

@Configuration
class AuthorizationServerConfig {

    @Bean
    fun authService (builder: RestTemplateBuilder) : RestTemplate {

        return builder.build()
    }
}