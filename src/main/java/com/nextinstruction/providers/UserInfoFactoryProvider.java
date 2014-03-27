package com.nextinstruction.providers;

import com.nextinstruction.security.AppUser;
import com.nextinstruction.security.UserInfo;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;
import org.glassfish.jersey.server.model.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.Principal;


@Singleton
public class UserInfoFactoryProvider extends AbstractValueFactoryProvider {

    protected static Logger LOG = LoggerFactory.getLogger(UserInfoFactoryProvider.class);


    @Inject
    public UserInfoFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator locator) {
        super(mpep, locator, Parameter.Source.UNKNOWN);
    }


    @Singleton
    public static final class InjectionResolver extends ParamInjectionResolver<AppUser> {
        public InjectionResolver() {
            super(UserInfoFactoryProvider.class);
        }
    }

    private static final class UserInfoFactory extends AbstractContainerRequestValueFactory<UserInfo> {

        @Override
        public UserInfo provide() {
            final Principal principal = getContainerRequest().getSecurityContext().getUserPrincipal();
            LOG.info("Providing user info: {}", principal);
            return (UserInfo) principal;
        }
    }

    @Override
    protected AbstractContainerRequestValueFactory<?> createValueFactory(Parameter parameter) {
        return new UserInfoFactory();
    }





}