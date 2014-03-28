#!/bin/bash

CLIENT_CERT_NAME=client
SERVER_CERT_NAME=server
SERVER_ALIAS=server
CLIENT_ALIAS=client
SERVER_DN='CN=localhost, OU=testing, O=testing, L=Annapolis, ST=MD, C=US'
CLIENT_DN='CN=cliff james, OU=testing, O=testing, L=Annapolis, ST=md, C=US'

mk_keystores() {
    echo "*** Generating client keypair***"
    keytool -genkey -keystore keystore_client.jks -alias client \
        -dname "${CLIENT_DN}" -storepass "secret" -validity 3650 \
        -keypass "secret"
    echo "*** Exporting client key ***"
    keytool -export -alias client -rfc -storepass "secret" \
        -keystore keystore_client.jks > ./client.cert
    echo "*** Creating Server truststore ***"
    keytool -import -alias client -file client.cert -noprompt -keystore  \
        truststore_server.jks  -keypass "secret" -storepass "secret" -trustcacerts
    echo "*** Generating server keypair ***"
    keytool -genkey -keystore keystore_server.jks -alias server \
        -dname "${SERVER_DN}" -storepass "secret" -validity 3650 \
        -keypass "secret"
    echo "*** Exporting server key ***"
    keytool -export -alias server -rfc -keystore keystore_server.jks \
        -storepass "secret" > ./server.cert
    echo "*** Creating client truststore ***"
    keytool -import -alias server -file ./server.cert -noprompt -keystore  \
        truststore_client.jks  -keypass "secret" -storepass "secret" -trustcacerts
}

jks_to_p12() {
    echo "*** Generating client PKCS12 file ***"
    keytool -importkeystore -srckeystore keystore_client.jks \
    -destkeystore client.p12 -srcstoretype JKS -deststoretype PKCS12 \
    -deststorepass secret -srcalias client -destalias clientKey \
    -srcstorepass secret
}

rm *.jks *.cert *.p12 2>/dev/null
mk_keystores
jks_to_p12
