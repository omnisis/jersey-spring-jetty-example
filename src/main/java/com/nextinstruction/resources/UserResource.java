package com.nextinstruction.resources;

import com.nextinstruction.security.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Singleton
@Path("/user")
public class UserResource {
    private Logger LOG = LoggerFactory.getLogger(UserResource.class);

    public UserResource() {
        LOG.info("UserResource Created!");
    }

    // Here we let Jersey inject a custom security context that was set in the CustomAuthFilter
    // The Principal Object in the security context is the one pushed in by the filter
    @Context
    private SecurityContext securityContext;


    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo showInfo() {
        final UserInfo userInfo = (UserInfo) securityContext.getUserPrincipal();
        return userInfo;
    }

    @GET
    @Path("badinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo badinfo() {
        final UserInfo userInfo = (UserInfo) securityContext.getUserPrincipal();
        return userInfo;
    }
}
