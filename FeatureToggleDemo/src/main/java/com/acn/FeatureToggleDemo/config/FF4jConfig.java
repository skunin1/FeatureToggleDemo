package com.acn.FeatureToggleDemo.config;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.InMemoryEventRepository;
import org.ff4j.property.store.InMemoryPropertyStore;
import org.ff4j.store.InMemoryFeatureStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class FF4jConfig {

    /* Creating the context method */

    public FF4j getFF4j(){
        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new InMemoryFeatureStore());//specifying where we want to store our feature information
        ff4j.setPropertiesStore(new InMemoryPropertyStore());//where to save custom properties
        ff4j.setEventRepository(new InMemoryEventRepository());//save all events occured in the feature toggler library
        ff4j.audit(true);
        ff4j.autoCreate(true);
        return ff4j;
    }


}
