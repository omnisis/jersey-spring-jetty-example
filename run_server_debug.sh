#!/bin/sh

DEBUG_PORT=5005
MVN=mvn
MAVEN_OPTS+="-Xdebug  -Xrunjdwp:transport=dt_socket,address=${DEBUG_PORT},server=y,suspend=n"
# Uncomment to debug HTTPS stuff
MAVEN_OPTS+=" -Djavax.net.debug=ssl,handshake"
echo "MAVEN_OPTS = ${MAVEN_OPTS}"
export MAVEN_OPTS
exec ${MVN} jetty:run
