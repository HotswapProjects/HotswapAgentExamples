package org.hotswap.agent.example;

/**
 * Hello world class to be replaced by extraClasspath.
 */
@HelloAnnotation("hello")
public class HelloWorld {
    public static String hello() {
        return "Hello World";
    }

    @HelloAnnotation("hello")
    public String hello(@HelloAnnotation("par") String par) {
        return "Hello " + par;
    }
}
