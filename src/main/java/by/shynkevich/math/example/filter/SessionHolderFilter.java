package by.shynkevich.math.example.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.shynkevich.math.example.service.ExampleService;
import com.google.common.collect.Maps;

/**
 * Session holder filter
 * <p>
 * Date: 2020-04-19
 *
 * @author Aliaksandr Shynkevich
 */
public class SessionHolderFilter implements Filter {

    private static final String SERVICE_KEY = "service";
    private static final String EXAMPLE_COOKIE = "exsession";
    private static final String ROOT_PATH = "/";
    private static final int ONE_DAY_BABY = 86400;
    private Map<String, ExampleService> serviceRegister;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(true);
        Optional<Cookie> cookieItem = extractCookie(httpRequest);
        Cookie sessionCookie = cookieItem.orElseGet(() -> {
            Cookie cookie = new Cookie(EXAMPLE_COOKIE, session.getId());
            cookie.setPath(ROOT_PATH);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(ONE_DAY_BABY);
            httpResponse.addCookie(cookie);
            return cookie;
        });

        ExampleService registeredService = serviceRegister.get(sessionCookie.getValue());
        boolean sessionIsRegistered = Objects.nonNull(registeredService);

        if (Objects.isNull(extractService(session))) {
            ExampleService service = serviceRegister.get(sessionCookie.getValue());
            if (sessionIsRegistered) {
                session.setAttribute(SERVICE_KEY, service);
            }
        }

        chain.doFilter(request, response);

        if (!sessionIsRegistered) {
            ExampleService exampleService = extractService(session);
            if (Objects.nonNull(exampleService)) {
                serviceRegister.put(sessionCookie.getValue(), exampleService);
            }
        }
    }

    private ExampleService extractService(HttpSession session) {
        return (ExampleService) session.getAttribute(SERVICE_KEY);
    }

    private Optional<Cookie> extractCookie(HttpServletRequest httpRequest) {
        return Stream.of(httpRequest.getCookies())
                .filter(cookie -> EXAMPLE_COOKIE.equals(cookie.getName()))
                .findAny();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        serviceRegister = Maps.newConcurrentMap();
    }

    @Override
    public void destroy() {
        serviceRegister.clear();
    }
}
