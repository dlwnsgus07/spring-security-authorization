package nextstep.app;

import java.util.PropertyResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;

public class MvcRequestMatcher implements RequestMatcher{

    private HttpMethod httpMethod;
    private String pattern;

    public MvcRequestMatcher(HttpMethod httpMethod, String pattern) {
        this.httpMethod = httpMethod;
        this.pattern = pattern;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (request.getMethod() == null) {
            return false;
        }
        return httpMethod.matches(request.getMethod()) && request.getRequestURI().equals(pattern);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPattern() {
        return pattern;
    }
}
