Jersey2-spring3-webapp
======================
This is an example java based webapp integrating jersey2 + Spring3 + Jetty 8.

Some things that are demonstrated include:
* Use of MavenJettyPlugin to launch Jetty from Maven
* Shell script to launch jetty with cmdline options for remote debugging
* Custom exception mapping in jersey
* Custom auth filtering in jersey
* Running

To run the the webapp:

    $> mvn jetty:run

To launch integration tests:

    $> mvn verify -Pitest

To launch the maven jetty app with remote debugging support

    $> sh ./run_server_debug.sh
