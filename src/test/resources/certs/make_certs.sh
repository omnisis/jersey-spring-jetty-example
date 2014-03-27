#!/bin/bash

CLIENT_CERT_NAME=client
SERVER_CERT_NAME=server
SERVER_ALIAS=server
CLIENT_ALIAS=client
SERVER_DN='CN=localhost, OU=testing, O=testing, L=Annapolis, ST=MD, C=US'
CLIENT_DN='CN=cliff james, OU=testing, O=testing, L=Annapolis, ST=md, C=US'

mk_keystores() {
    echo "Creating server cert"
    keytool -genkey -alias ${SERVER_ALIAS} -keyalg RSA -keystore ${SERVER_CERT_NAME}.jks -storepass secret \
        -dname "${SERVER_DN}" -keypass secret
    echo "Creating client cert"
    keytool -genkey -alias ${CLIENT_ALIAS} -keyalg RSA -keystore ${CLIENT_CERT_NAME}.jks -storepass secret \
    -dname "${CLIENT_DN}" -keypass secret
}

export_keys() {
   echo "Exporting server key"
   keytool -export -file ${SERVER_CERT_NAME}.cert -keystore \
       ${SERVER_CERT_NAME}.jks -storepass secret -alias ${SERVER_ALIAS}
   echo "Exporting client key"
   keytool -export -file ${CLIENT_CERT_NAME}.cert -keystore \
       ${CLIENT_CERT_NAME}.jks -storepass secret -alias ${CLIENT_ALIAS}
}

import_keys() {
    echo "Importing client cert into server truststore"
    cp ${CLIENT_CERT_NAME}.jks ${SERVER_CERT_NAME}-trust.jks
    #keytool -import -file ${CLIENT_CERT_NAME}.cert -keystore \
    #    ${SERVER_CERT_NAME}-trust.jks -storepass secret -alias ${CLIENT_ALIAS} \
    #    -trustcacerts -noprompt -keypass secret
    #echo "importing server key into client keystore"
    #keytool -import -file ${SERVER_CERT_NAME}.cert -keystore \
    #    ${CLIENT_CERT_NAME}.jks -storepass secret -alias ${SERVER_ALIAS} \
    #    -trustcacerts -noprompt -keypass secret
}

jks_to_p12() {
    keytool -importkeystore -srckeystore ${CLIENT_CERT_NAME}.jks \
    -destkeystore ${CLIENT_CERT_NAME}.p12 -srcstoretype JKS -deststoretype PKCS12 \
    -deststorepass secret -srcalias client -destalias ${CLIENT_ALIAS} \
    -srcstorepass secret
}

rm *.jks *.cert *.p12 2>/dev/null
mk_keystores
export_keys
import_keys
jks_to_p12
