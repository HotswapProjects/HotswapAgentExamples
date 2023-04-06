Spring + Hibernate + Spring MVC
===============================

Example of application setup with:
* Spring 35.3.26
* Hibernate Entity Manager 5.4.33
* Spring WebMVC

You need to specify java startup properties with dcevm and javaagent:
<pre>java -javaagent:HotswapAgent.jar</pre>

It is preconfigured with maven jetty plugin, run:
* mvn clean package
* mvn jetty:run
* launch web browser on http://localhost:8080/

The application is preconfigured for automatic hotswap deployment (see autoHotswap property in hotswap-agent.properties).
If you specify java startup parameters, all changes to class file will be automatically reloaded:
 <pre>java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000</pre>
If you start the application in debug mode, autoHotswap will be disabled and you should use IDE hotswap feature instead.

To enable debug of Hotswap reload on JVM level, add -Xlog:redefine+class*=debug java startup parameter:
<pre>java -javaagent:..\HotswapAgent\target\HotswapAgent.jar -Xlog:redefine+class*=debug</pre>
