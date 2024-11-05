package nextstep.security.authorization;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nextstep.security.authentication.Authentication;
import nextstep.security.authentication.AuthenticationException;
import nextstep.security.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationFilter extends OncePerRequestFilter {

    AuthorizationManager<HttpServletRequest> authorizationManager = new RequestAuthorizationManager();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AuthorizationDecision decision = authorizationManager.check(authentication, request);
            if (decision == null || !decision.isGranted()) {
                throw new ForbiddenException();
            }
            filterChain.doFilter(request, response);
        } catch (ForbiddenException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
