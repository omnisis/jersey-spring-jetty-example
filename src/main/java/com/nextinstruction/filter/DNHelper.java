package com.nextinstruction.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import javax.servlet.http.HttpServletRequest;


public class DNHelper {

    private static final Logger log = LoggerFactory.getLogger(DNHelper.class);

    public static Principal getDNFromRequest(HttpServletRequest request) {
        X509Certificate certs[] = null;
        X509Certificate clientCert = null;
        try {

            certs = (X509Certificate[]) request.getAttribute(
                    "javax.servlet.request.X509Certificate"
            );
            if (certs != null) {
                clientCert = certs[0];


                    log.info("Subject Name: {}",
                            clientCert.getSubjectDN().getName());

                    log.info("Issuer Name: {}",
                            clientCert.getIssuerDN().getName());
                    try {

                        clientCert.checkValidity();
                        log.info("Certificate is valid");
                    } catch (CertificateExpiredException cee) {
                        log.info("Certificate is expired !!!");
                    } catch (CertificateNotYetValidException cnyv) {
                        log.info("Certificate is not yet valid !!!");
                    }

            } else {
                log.warn("No SSL client certificate available");
            }
        } catch (ClassCastException cce) {
            log.warn("@@@ Problems with the certificate: {}", cce.getMessage(), cce);
        }

        return (clientCert == null) ?  null : clientCert.getIssuerDN();

    }
}
