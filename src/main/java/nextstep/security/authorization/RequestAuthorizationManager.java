package nextstep.security.authorization;

import javax.servlet.http.HttpServletRequest;
import nextstep.security.authentication.Authentication;
import nextstep.security.authentication.AuthenticationException;

public class RequestAuthorizationManager implements AuthorizationManager<HttpServletRequest>{

    @Override
    public AuthorizationDecision check(Authentication authentication, HttpServletRequest request) {
        if (request.getRequestURI().equals("/members")) {
            if (authentication == null) {
                throw new AuthenticationException();
            }
            return new AuthorizationDecision(authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.equals("ADMIN")));
        }
        if (request.getRequestURI().equals("/members/me")) {
            if (authentication == null) {
                throw new AuthenticationException();
            }
            return new AuthorizationDecision(authentication.isAuthenticated());
        }

        if (request.getRequestURI().equals("/search")) {
            return new AuthorizationDecision(true);
        }
        return new AuthorizationDecision(false);
    }
}
