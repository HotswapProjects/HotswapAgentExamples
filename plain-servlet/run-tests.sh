#!/bin/sh
# simple script to run various containers setup
# this should be replaced by build sever in the future

# fail with first failed test
set -e

# run clean install (with all integration tests)
function test {
    echo "Running with container $1"
    mvn -Dhotswapagent.jar=$2 clean verify -P $1
}

if [ -z "$HOT_SWAP_AGENT"]; then
  echo "HOT_SWAP_AGENT is not set, pls. specify where the HotSwapAgent jar location"
  exit 1
fi

# Older versions of Jetty works as well, but are configured only with embedded configuration, which does not support jvmargs
#  it cannot be launched automatically (set -javaagent). To launch the test, set MAVEN_OPTS and run manually.
test jetty7 ${HOT_SWAP_AGENT}
test jetty8 ${HOT_SWAP_AGENT}
test jetty9 ${HOT_SWAP_AGENT}

# Tomcat should be Ok.
test tomcat6 ${HOT_SWAP_AGENT}
test tomcat7 ${HOT_SWAP_AGENT}
test tomcat8 ${HOT_SWAP_AGENT}
test tomcat9 ${HOT_SWAP_AGENT}

# Almost all hotswap agent properties do not work :-(
#test jboss5x
# JBoss 8 is OSGI based and catalina WebappLoader is not used anymore
#test jboss8

# GlassFish is based on OSGI, need to first resolve
#test glassfish3
#test glassfish4

# Need to download manually and setup, did not try yet
# test weblogic12x