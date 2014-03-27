package com.nextinstruction.binders;

import com.nextinstruction.providers.UserInfoFactoryProvider;
import com.nextinstruction.security.AppUser;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

import javax.inject.Singleton;

public class MyBinder extends AbstractBinder {

        @Override
        protected void configure() {
            bind(UserInfoFactoryProvider.class).to(ValueFactoryProvider.class).in(Singleton.class);
            bind(UserInfoFactoryProvider.InjectionResolver.class)
                    .to(new TypeLiteral<InjectionResolver<AppUser>>() {
                    }).in(Singleton.class);
        }
    }