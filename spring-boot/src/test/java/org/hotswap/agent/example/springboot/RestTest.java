package org.hotswap.agent.example.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hotswap.agent.example.springboot.BasicSpringBootTest.swapClasses;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void exampleTest() throws Exception {
        this.webClient.get().uri("/hello").exchange().expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello");

        swapClasses(TestRest.class, TestRest2.class);

        this.webClient.get().uri("/hello").exchange().expectStatus().is4xxClientError();

        this.webClient.get().uri("/hello2").exchange().expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello2");

    }

}
