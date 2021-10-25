package com.acn.FeatureToggleDemo.config;

import org.ff4j.audit.repository.InMemoryEventRepository;
import org.ff4j.property.store.InMemoryPropertyStore;
import org.ff4j.spring.boot.web.api.config.EnableFF4jSwagger;
import org.ff4j.store.InMemoryFeatureStore;
import org.ff4j.FF4j;
import org.ff4j.web.FF4jDispatcherServlet;
import org.ff4j.web.embedded.ConsoleServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFF4jSwagger
@ConditionalOnClass({ConsoleServlet.class, FF4jDispatcherServlet.class})
public class FF4JWebConfiguration extends SpringBootServletInitializer {



@Bean
public ServletRegistrationBean<FF4jDispatcherServlet> ff4jDispatcherRegistration(FF4jDispatcherServlet dispatcherServlet){
    ServletRegistrationBean<FF4jDispatcherServlet> registrationBean = new
            ServletRegistrationBean<FF4jDispatcherServlet>(dispatcherServlet, "/ff4j-web-console/*");
    registrationBean.setName("ff4j-console");
    registrationBean.setLoadOnStartup(1);
    return registrationBean;
}

/*Enabling Admin Console */
    @Bean
    @ConditionalOnMissingBean
    public FF4jDispatcherServlet getFF4jDispatcherServlet(){
        FF4jDispatcherServlet consoleServlet = new FF4jDispatcherServlet();
        consoleServlet.setFf4j(getFF4j());
        return consoleServlet;
    }

    /* Creating the context method */
    @Bean
    public FF4j getFF4j(){
        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new InMemoryFeatureStore());//specifying where we want to store our feature information
        ff4j.setPropertiesStore(new InMemoryPropertyStore());//where to save custom properties
        ff4j.setEventRepository(new InMemoryEventRepository());//save all events occurred in the feature toggle library
        ff4j.audit(true);
        ff4j.autoCreate(true);
        return ff4j;
    }

    /*@Bean
    @SuppressWarnings({"rawtypes","unchecked"})
    public ServletRegistrationBean registerFf4jServlet(FF4jDispatcherServlet ff4jDispatcher){
        return new ServletRegistrationBean(ff4jDispatcher, "/ff4j-web-console/*");
    }*/
}
