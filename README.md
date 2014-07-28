HotswapAgent example applications
=================================

Example applications for HotswapAgent plugins.

The purpose of an example application is:
* complex automate integration tests (check various configurations before a release, see `run-tests.sh` script) 
* to check "real world" plugin usage during plugin development (i.e. inside container)
* to provide working solution for typical application setups
* sandbox to simulate issues for existing or new setups

Feel free to fork/branch and create an application for your setup (functional, but as simple as possible).
General setups will be merged into the master.

# plain-java
This example uses only core agent library and runs without any framework support. Check this example
to learn basic agent properties usage (extraClasspath, watchResources, autoHotswap).

# plain-servlet
Run hotwap agent with a servlet container/J2EE server (Jetty/Tomcat/JBoss). This example does pretty the same
as plain-java, but checks the value as HTTP response strings. This examples checks also that various application 
server versions are supported.

# custom-plugin
Detail explanation and example of how to create custom plugin even inside your application. 

# spring-hibernate
Complex project with typical setup Spring + Spring MVC + Hibernate. 

# seam-hibernate-jsf

# cdi-hibernate-jsf
