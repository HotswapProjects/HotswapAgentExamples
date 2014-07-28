Plain java example
==================

Example of application setup with plain Java (without any framework) and core HotswapAgent features.

Unit tests include:

* extraClasspath property - load classes and resources from alternative location 
* watchResources property - load a resource from alternative location only after it is modified
* autoHotswap property - change class definition at runtime after .class file is modified

Maven-antrun is used to compile/copy files from java-extra, java-hotswap and resources-extra folders into 
target folders to simulate extraClasspath/watchResources directories.

This example depends only on hotswap-agent-core (runs with `-XXaltjvm=dcevm -javaagent:hotswap-agent-core.jar`).

### extraClasspath

1. Class `org.hotswap.agent.example.HelloWorld` is located in both src/main/java and src/main/java-extra, 
on project assembly (`mvn package`) it becomes target/classes and target/extra. 
1. The agent is configured to add extra classpath directory target/extra 
(hotswap-agent.properties contains property `extraClasspath=target/extra`). 
1. Class definition from target/extra  should hence precede definition from target/classes 
1. HelloWorld.hello() should return "Hello World Extra".

Similarly for resources - directory resources/extra becomes target/extra and file test.properties should
be loaded from target/extra instead of target/classes.

### watchResources
Some resources may be modified during assembly process from src/main/resources to target/classes (e.g. maven resource
filtering) and hence it is not possible to use extraClasspath to watch directly src/main/resources. Use instead
watchProperties to return resource from the directory after a change only. It is up the user to modify only resources
without filtering.

In this example target/classes/testWatch.properties contains "Hello World" and target/watch/testWatch.properties
contains "This should never happen - before change contents should be read from target/classes." As the string
suggests, although the hotswap-agent.properties setup contains `watchResources=target/watch, original file
should be returned. Only after the test "manually" replaces target/watch/testWatch.properties with 
target/watch/testWatchReplace.properties (and hence IS modified), new value "Hello World Watch" is returned.

### autoHotswap
If hotswap-agent.properties setup contains `autoHotswap=true`, all class files on classpath (original + 
extraClasspath) are watched for changes (note that only plain .class files on local filesystem and not in JAR can
be watched). If the filesystem triggers a change, JVM hotswap is automatically launched for the file.
 
HelloWorldHotswapTest loads the class `org.hotswap.agent.example.HelloWorldHotswap` bytecode, renames
it to HelloWorld and replaces HelloWorldHotswap.class file in target/classes. This triggers the change
and new class version is introduced. HelloWorld.hello() will then return new value "Hello World Hotswap".
 