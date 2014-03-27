package com.nextinstruction.filter;

import com.nextinstruction.security.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Provider
// default priority is 'USER' which is too late in the filter processing chain
@Priority(Priorities.AUTHORIZATION)
public class CustomAuthFilter implements ContainerRequestFilter {
    private Logger LOG = LoggerFactory.getLogger(CustomAuthFilter.class);


    // May used to get attributes that may not be available on the container request context
    // (e.g. Session Parameters)
    @Context
    HttpServletRequest webRequest;

    //@Inject HttpSession webSession;

    public CustomAuthFilter() {
        LOG.info("Initializing Custom Authorization Filter...");
    }


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        // TODO: add code to maybe read some values off the incoming request or consult
        // LDAP, etc. to determine auth.
        //
        //

        LOG.trace("sessionId: " + webRequest.getSession().getId());
        LOG.info("AuthType: " + webRequest.getAuthType());
        final Principal userPrincipal = DNHelper.getDNFromRequest(webRequest);

        final String requestPath = containerRequestContext.getUriInfo().getRequestUri().getPath();
        LOG.trace("RequestURI Path: {} ", requestPath);

        // TODO: Use custom auth logic to abort the current connection with an HTTP
        // unauthorized error when an unauthorized user shows up.  Here we merely show an
        // example by denying any user who hits a particular path
        if(requestPath.equals("/user/badinfo")) {
            containerRequestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("User is not authorized!")
                        .build());
        }


        // here we set a custom security context
        LOG.debug("Setting customSecurityContext");
        containerRequestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return userPrincipal;
            }

            @Override
            public boolean isUserInRole(String role) {
                return false;
            }

            @Override
            public boolean isSecure() {
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                return "https";
            }
        });




    }
}
