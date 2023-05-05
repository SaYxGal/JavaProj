package com.labwork01.app.configuration;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    public static final String REST_API = "/api";
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        ViewControllerRegistration registration = registry.addViewController("/notFound");
        registration.setViewName("forward:/index.html");
        registration.setStatusCode(HttpStatus.OK);
        registry.addViewController("login");

        // Alternative way (404 error hits the console):
        // > registry.addViewController("/notFound").setViewName("forward:/index.html");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notFound"));
        };
    }
}
