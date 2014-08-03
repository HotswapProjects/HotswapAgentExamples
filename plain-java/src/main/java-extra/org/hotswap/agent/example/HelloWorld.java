package org.hotswap.agent.example;

/**
 * Hello world class.
 */
@HelloAnnotation("hello extra")
public class HelloWorld {
    public static String hello() {
        return "Hello World Extra";
    }

    @HelloAnnotation("hello extra")
    public String hello(@HelloAnnotation("par extra") String par) {
        return "Hello Extra " + par;
    }
}
