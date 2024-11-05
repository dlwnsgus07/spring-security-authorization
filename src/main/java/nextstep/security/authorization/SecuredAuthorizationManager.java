package nextstep.security.authorization;

import java.lang.reflect.Method;
import nextstep.security.authentication.Authentication;
import nextstep.security.authentication.AuthenticationException;
import nextstep.security.authorization.aop.Secured;
import nextstep.security.context.SecurityContextHolder;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class SecuredAuthorizationManager implements AuthorizationManager<MethodInvocation>{

    @Override
    public AuthorizationDecision check(Authentication authentication, MethodInvocation invocation) {
        Method method = invocation.getMethod();
        String secured = method.getAnnotation(Secured.class).value();
        if (authentication == null) {
            throw new AuthenticationException();
        }
        if (!authentication.getAuthorities().contains(secured)) {
            throw new ForbiddenException();
        }
        return new AuthorizationDecision(true);
    }
}
