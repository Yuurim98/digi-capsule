package io.github.Yuurim98.digi_capsule.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String ALLOWED_ORIGINS = "http://localhost:3000";
    private static final String[] ALLOWED_METHODS = { "GET", "POST", "PUT", "DELETE", "OPTIONS" };
    private static final String ALLOWED_HEADERS = "*";
    private static final long MAX_AGE_SECS = 3600L;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(ALLOWED_ORIGINS)
            .allowedMethods(ALLOWED_METHODS)
            .allowedHeaders(ALLOWED_HEADERS)
            // .allowCredentials(true)
            .maxAge(MAX_AGE_SECS);
    }

}
