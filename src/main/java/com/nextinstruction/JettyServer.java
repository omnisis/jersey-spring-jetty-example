package com.nextinstruction;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;


public class JettyServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server();

        WebAppContext context = new WebAppContext();
        context.setDescriptor(context + "/WEB-INF/web.xml");
        context.setResourceBase("../jersey2-spring3-webapp/src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        final SelectChannelConnector httpConn = new SelectChannelConnector();
        httpConn.setConfidentialScheme("https");
        httpConn.setPort(8181);
        httpConn.setConfidentialPort(8443);

        final SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath("src/main/resources/server.jks");
        sslContextFactory.setKeyStorePassword("secret");
        sslContextFactory.setKeyStoreType("JKS");
        //sslContextFactory.setTrustAll(true); // false?
        sslContextFactory.setTrustStore("src/main/resources/server-trust.jks");
        sslContextFactory.setTrustStorePassword("secret");
        sslContextFactory.setTrustStoreType("JKS");
        sslContextFactory.setNeedClientAuth(true);
        sslContextFactory.setWantClientAuth(true);
//        sslContextFactory.setValidateCerts(true);
        SslSelectChannelConnector sslNIOConnector = new SslSelectChannelConnector(sslContextFactory);
        sslNIOConnector.setPort(8443);

        ///server.addConnector(httpConn);
        server.setConnectors(new Connector[]{httpConn, sslNIOConnector});

        server.start();
        server.join();
    }
}
