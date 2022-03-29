package by.shynkevich.math.system;

import java.security.KeyStore;
import java.security.KeyStoreException;

import by.shynkevich.math.example.filter.SessionHolderFilter;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
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
    private static final String HTTP_SCHEME = "http";
    private static final String CONFIDENTIAL_USER_CONSTRAINT = "CONFIDENTIAL";
    private static final String PATTERN = "/*";

    static {
        try {
            KeyStore keyStore = KeyStore.getInstance("PEM");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    @Value("${exampler.redirect-port}")
    private int redirectPort;

    @Bean
    public FilterRegistrationBean<SessionHolderFilter> loggingFilter() {
        FilterRegistrationBean<SessionHolderFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SessionHolderFilter());
        registrationBean.addUrlPatterns(ROOT_PATH_WILDCARD);

        return registrationBean;
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint(CONFIDENTIAL_USER_CONSTRAINT);
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern(PATTERN);
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(getHttpConnector());
        return tomcat;
    }

    private Connector getHttpConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme(HTTP_SCHEME);
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(redirectPort);
        return connector;
    }
}
