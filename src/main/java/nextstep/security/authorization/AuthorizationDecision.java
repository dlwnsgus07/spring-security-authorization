package nextstep.security.authorization;

public class AuthorizationDecision implements AuthorizationResult{

    private final boolean granted;

    public AuthorizationDecision(boolean granted) {
        this.granted = granted;
    }

    @Override
    public boolean isGranted() {
        return granted;
    }
}
