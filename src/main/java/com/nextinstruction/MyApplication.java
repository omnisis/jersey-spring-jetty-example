package com.nextinstruction;

import com.nextinstruction.binders.MyBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("resources")
public class MyApplication extends ResourceConfig {

    private Logger LOG = LoggerFactory.getLogger(MyApplication.class);


    public MyApplication() {
        LOG.info("MyApplication started!");

        // Turn on Jersery classpath scanning for providers and resources in the
        // given package directories
        packages("com.nextinstruction");

        // Jackson JSON marshalling
        register(JacksonFeature.class);

        register(new MyBinder());
    }
}
