package com.supernet.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpAuth entry point
 */
@Component
public class HttpAuthenticationEntryPoint
        extends BasicAuthenticationEntryPoint {

    /**
     * Set realm name
     */
    public HttpAuthenticationEntryPoint() {
        setRealmName("ESHOP");
    }

    /**
     * Set realm name
     *
     * @param realmName name
     */
    public HttpAuthenticationEntryPoint(String realmName) {
        setRealmName(realmName);
    }

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException,
            ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
