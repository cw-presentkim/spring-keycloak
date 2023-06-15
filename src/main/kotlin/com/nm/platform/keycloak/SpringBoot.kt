package com.nm.platform.keycloak

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class SpringBoot {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(SpringBoot::class.java, *args)
        }
    }
}