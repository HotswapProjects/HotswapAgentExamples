#!/bin/sh
# simple script to run all tests and all versions. It fails with first failure
# this should be replaced by build sever in the future

# fail with first failed test
set -e

function test {
    echo "Running with Java $1"
    export JAVA_HOME=$1

    echo "Resolved version: " `"$JAVA_HOME/bin/java" -version`

    mvn -Dhotswapagent.jar=$2 clean package

    # run tests
    cd plain-java; ./run-tests.sh; cd ..

    # TODO
    # custom-plugin
    # cd plain-servlet; ./run-tests.sh; cd ..
    # spring-hibernate
    # ...
}

if [ -z "$JAVA_HOME" ]; then
    echo "JAVA_HOME is not set"
    exit 1
fi

if [ -z "$HOT_SWAP_AGENT"]; then
  echo "HOT_SWAP_AGENT is not set, pls. specify where the HotSwapAgent jar location"
  exit 1
fi

test $JAVA_HOME $HOT_SWAP_AGENT
