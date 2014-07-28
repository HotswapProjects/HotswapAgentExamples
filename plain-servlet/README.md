Plain servlet example
=====================

Example of application setup inside servlet containser / J2EE application server (without any 
additional framework) and HotswapAgent including all plugins.

Unit tests include:

* extraClasspath property - load classes and resources from alternative location 
* watchResources property - load a resource from alternative location only after it is modified
* autoHotswap property - change class definition at runtime after .class file is modified

Maven-antrun is used to compile/copy files from java-extra, java-hotswap and resources-extra folders into 
target folders to simulate extraClasspath/watchResources directories.

This example does pretty the same as plain-java, but checks the value as HTTP response strings.
See plain-java/README.md for description of used hotswap-agent.properties. 

This example depends on hotswap-agent.jar (runs with `-XXaltjvm=dcevm -javaagent:hotswap-agent.jar`) to
check that all plugins are 

Application server support
==========================

