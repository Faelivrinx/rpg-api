package com.mypieceofcode.rpgapi.config

import com.mypieceofcode.rpgapi.application.user.AuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class AuthFilter : Filter {

    @Autowired
    lateinit var authorizationService : AuthorizationService

    private final val HEADER_TOKEN = "Authorization"


    @Throws(ServletException::class)
    override fun init(filterConfig: javax.servlet.FilterConfig?) {
    }

    private final val UNSECURED_ENPOINTS = arrayOf(
            "/auth/login",
            "/auth/register"
    )

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        if(!allowUnauthorized(request as HttpServletRequest)) {
            if (!containsValidToken(request)) {
                with(response as HttpServletResponse) {
                    reset()
                    status = HttpServletResponse.SC_UNAUTHORIZED
                    return
                }
            }
        }
        chain?.doFilter(request, response)
    }

    private fun containsValidToken(request: HttpServletRequest): Boolean {
        var token = request.getHeader(HEADER_TOKEN)
        if(token == null){
            token = getTokenFromParams(request)
            if(token == null){
                return false
            }
        }
        return authorizationService.validToken(token)

    }

    private fun getTokenFromParams(request: HttpServletRequest) : String?{
        return request.getParameter(HEADER_TOKEN)
    }

    private fun allowUnauthorized(request: HttpServletRequest) : Boolean{
        return HttpMethod.OPTIONS.matches(request.method) || isUnauthorizedRequest(request)
    }


    private fun isUnauthorizedRequest(request: HttpServletRequest) : Boolean{
        return UNSECURED_ENPOINTS.contains(request.requestURI)
    }

    override fun destroy() {

    }

}
