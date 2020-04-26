package by.shynkevich.math.system;

import by.shynkevich.math.example.filter.SessionHolderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * System configuration bean.
 * <p>
 * Date: 2020-04-21
 *
 * @author Aliaksandr Shynkevich
 */
@Configuration
public class SystemConfiguration {

    private static final String ROOT_PATH_WILDCARD = "/*";

    @Bean
    public FilterRegistrationBean<SessionHolderFilter> loggingFilter() {
        FilterRegistrationBean<SessionHolderFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SessionHolderFilter());
        registrationBean.addUrlPatterns(ROOT_PATH_WILDCARD);

        return registrationBean;
    }
}
