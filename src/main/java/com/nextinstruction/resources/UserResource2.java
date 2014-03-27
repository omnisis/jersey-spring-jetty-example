package com.nextinstruction.resources;

import com.nextinstruction.security.AppUser;
import com.nextinstruction.security.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user2")
public class UserResource2 {
    private Logger LOG = LoggerFactory.getLogger(UserResource2.class);

    public UserResource2() {
        LOG.info("UserResource Created!");
    }

    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo showInfo(@AppUser UserInfo userInfo) {
        return userInfo;
    }

    @GET
    @Path("badinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo badinfo() {
        return null;
    }
}
