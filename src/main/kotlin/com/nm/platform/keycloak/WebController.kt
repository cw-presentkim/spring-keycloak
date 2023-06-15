package com.nm.platform.keycloak

import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal
import javax.servlet.http.HttpServletRequest

@Controller
class WebController(private val clientService: OAuth2AuthorizedClientService) {

    @GetMapping(path = ["/"])
    fun index(): String {
        return "external"
    }

    @GetMapping("/logout")
    @Throws(Exception::class)
    fun logout(request: HttpServletRequest): String {
        request.logout()
        return "redirect:/"
    }

    @GetMapping(path = ["/customers"])
    fun customers(principal: Principal, model: Model, authentication: Authentication): String {

        model.addAttribute("username", principal.name)
        val oauthToken = authentication as OAuth2AuthenticationToken
        val client: OAuth2AuthorizedClient = clientService.loadAuthorizedClient(
            oauthToken.authorizedClientRegistrationId,
            oauthToken.name
        )
        logger.info("accessToken => {}", client.accessToken.tokenValue)
        logger.info("refreshToken => {}", client.refreshToken?.tokenValue ?: "")
        return "customers"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(WebController::class.java)
    }

}