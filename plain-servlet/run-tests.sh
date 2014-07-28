#!/bin/sh
# simple script to run various containers setup
# this should be replaced by build sever in the future

# fail with first failed test
set -e

# run clean install (with all integration tests)
function test {
    echo "Running with container $1"
    mvn clean install -P $1
}

# Older versions of Jetty works as well, but are configured only with embedded configuration, which does not support jvmargs
#  it cannot be launched automatically (set -javaagent). To launch the test, set MAVEN_OPTS and run manually.
# test jetty4
# test jetty5
test jetty6
test jetty7
test jetty8
test jetty9

# Tomcat should be Ok.
test tomcat6
test tomcat7
test tomcat8

# Almost all hotswap agent properties do not work :-(
#test jboss5x
# JBoss 8 is OSGI based and catalina WebappLoader is not used anymore
#test jboss8

# GlassFish is based on OSGI, need to first resolve
#test glassfish3
#test glassfish4

# Need to download manually and setup, did not try yet
# test weblogic12x