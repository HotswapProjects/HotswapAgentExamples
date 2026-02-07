#!/bin/sh
set -e

if [ -z "$WILDFLY_HOME" ]; then
  echo "WILDFLY_HOME is not set. Point it to your WildFly installation." >&2
  exit 1
fi

mvn clean package
cp "target/deltaspike-hibernate-jsf-wildfly-0.0.1-SNAPSHOT.war" "$WILDFLY_HOME/standalone/deployments/"

cat <<'MSG'
Deployed WAR to WildFly.
Make sure WildFly is started with DCEVM + HotswapAgent, for example:
  -XXaltjvm=dcevm -javaagent:/path/to/hotswap-agent.jar
MSG
