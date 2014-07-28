#!/bin/sh
# simple script to run test
# this should be replaced by build sever in the future

# fail with first failed test
set -e

# run clean install (with all integration tests)
function test {
    echo "Running tests for plain Java"
    mvn clean install
}

test