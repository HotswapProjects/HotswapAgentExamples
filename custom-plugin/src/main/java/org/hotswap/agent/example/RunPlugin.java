package org.hotswap.agent.example;

import org.hotswap.agent.example.service.HelloWorldService;

public class RunPlugin {
    public static void main(String[] args) throws InterruptedException {
        HelloWorldService helloWorldService = new HelloWorldService();
        while(true) {
            System.out.print(helloWorldService.getExamplePluginResourceText());
            System.out.print(",");
            System.out.print(helloWorldService.getLoadedClasses());
            System.out.print(",");
            System.out.print(helloWorldService.getReloadedClasses());
            System.out.println(";");
            Thread.sleep(1000);
        }
    }
}
