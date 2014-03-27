package com.nextinstruction.providers;

import org.glassfish.hk2.api.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Priorities;


//@Provider
@Priority(Priorities.AUTHENTICATION)
public class HttpSessionFactory implements Factory<HttpSession> {
    private final HttpServletRequest request;
    private static final Logger LOG = LoggerFactory.getLogger(HttpSessionFactory.class);

    @Inject
    public HttpSessionFactory(HttpServletRequest request) {
        LOG.info("Created HttpSessionFactory!");
        this.request = request;
    }

    @Override
    public HttpSession provide() {
        return request.getSession();
    }

    @Override
    public void dispose(HttpSession httpSession) {
    }
}
