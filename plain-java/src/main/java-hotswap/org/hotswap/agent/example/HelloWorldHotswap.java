package org.hotswap.agent.example;

import java.lang.Cloneable;

/**
 * Hello world class.
 */
@HelloAnnotation("hello hotswap")
public class HelloWorldHotswap {
    public static String hello() {
        return helloPrivate();
    }

    @HelloAnnotation("hello hotswap")
    public String hello(final @HelloAnnotation("par hotswap") String par) {
        HelloInterface anonymous = new HelloInterface() {
            @Override
            @HelloAnnotation("hello hotswap")
            public String hello(final @HelloAnnotation("par hotswap") String dummy) {
                return "Hello Hotswap " + par;
            }
        };
        return anonymous.hello(null);
    }

    // chage ordering of annonymous classes. HotswapperPlugin should kick in.
    public void dummy() {
        new Cloneable() {};
    }

    private static String helloPrivate() {
        return "Hello World Hotswap";
    }

}
