package nextstep.security.authorization.aop;

import java.lang.reflect.Method;
import nextstep.security.authentication.Authentication;
import nextstep.security.authentication.AuthenticationException;
import nextstep.security.authorization.AuthorizationManager;
import nextstep.security.authorization.ForbiddenException;
import nextstep.security.authorization.SecuredAuthorizationManager;
import nextstep.security.context.SecurityContextHolder;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class SecuredAspect {

    AuthorizationManager<MethodInvocation> authorizationManager = new SecuredAuthorizationManager();

    @Before("@annotation(nextstep.security.authorization.aop.Secured)")
    public void checkSecured(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MethodInvocation methodInvocation = (MethodInvocation) joinPoint;
        authorizationManager.check(authentication, methodInvocation);
    }
}
