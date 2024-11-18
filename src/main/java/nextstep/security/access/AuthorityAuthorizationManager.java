package nextstep.security.access;

import nextstep.security.authentication.Authentication;
import nextstep.security.authentication.AuthenticationException;
import nextstep.security.authorization.AuthorizationDecision;
import nextstep.security.authorization.AuthorizationManager;

public class AuthorityAuthorizationManager<T>  implements AuthorizationManager<T> {

    private final String authority;

    public AuthorityAuthorizationManager(String authority) {
        this.authority = authority;
    }

    @Override
    public AuthorizationDecision check(Authentication authentication, T object) {
        if (authentication == null) {
            throw new AuthenticationException();
        }
        boolean isGranted = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.equals(this.authority));

        return new AuthorizationDecision(isGranted);
    }
}
