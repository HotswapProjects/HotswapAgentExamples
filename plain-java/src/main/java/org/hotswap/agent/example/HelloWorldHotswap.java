package org.hotswap.agent.example;

/**
 * Hello world class to be replaced by hotswap.
 */
@HelloAnnotation("hello")
public class HelloWorldHotswap {
    public static String hello() {
        return "Hello World";
    }

    // create new annonymous class before HelloInterface. HotswapperPlugin should kick in.
    public void dummy() {
        new Cloneable() {};
    }

    @HelloAnnotation("hello")
    public String hello(final @HelloAnnotation("par") String par) {
        HelloInterface anonymous = new HelloInterface() {
            @Override
            public String hello(String dummy) {
                return "Hello " + par;
            }
        };
        return anonymous.hello(null);
    }
}
