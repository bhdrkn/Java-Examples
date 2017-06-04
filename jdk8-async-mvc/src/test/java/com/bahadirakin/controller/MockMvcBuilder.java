package com.bahadirakin.controller;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

final class MockMvcBuilder {

    private Object service;
    private String basePackage;
    private Optional<Class<?>> exceptionHandler = Optional.empty();

    MockMvcBuilder withService(Object service) {
        this.service = service;
        return this;
    }

    MockMvcBuilder withExceptionHandlerSupport(String basePackage, Class<?> exceptionHandler) {
        this.basePackage = basePackage;
        this.exceptionHandler = Optional.ofNullable(exceptionHandler);
        return this;
    }

    MockMvc build() {
        checkNotNull(service);

        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(service);
        exceptionHandler.ifPresent(aClass -> {
            final StaticApplicationContext staticApplicationContext = new StaticApplicationContext();
            final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
            final Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition candidateComponent : candidateComponents) {
                staticApplicationContext.registerBeanDefinition(candidateComponent.getBeanClassName(), candidateComponent);
            }

            final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
            webMvcConfigurationSupport.setApplicationContext(staticApplicationContext);

            final ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
            exceptionHandlerExceptionResolver.setApplicationContext(staticApplicationContext);

            builder.setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver());
        });

        return builder.build();
    }
}
