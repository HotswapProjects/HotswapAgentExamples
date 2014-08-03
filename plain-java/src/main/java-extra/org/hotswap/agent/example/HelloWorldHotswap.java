package org.hotswap.agent.example;

/**
 * Hello world class to be replaced by hotswap.
 */
@HelloAnnotation("hello extra")
public class HelloWorldHotswap {
    public static String hello() {
        return "Hello World Extra";
    }

    // create new annonymous class before HelloInterface. HotswapperPlugin should kick in.
    public void dummy() {
        new Cloneable() {};
    }

    @HelloAnnotation("hello extra")
    public String hello(final @HelloAnnotation("par extra") String par) {
        HelloInterface anonymous = new HelloInterface() {
            @Override
            public String hello(String dummy) {
                return "Hello Extra " + par;
            }
        };
        return anonymous.hello(null);
    }
}
