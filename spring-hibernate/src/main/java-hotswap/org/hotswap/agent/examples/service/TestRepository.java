package org.hotswap.agent.example.service;

import org.springframework.stereotype.Repository;

/**
 * Hello world repository to be replaced by hotswap
 */
@Repository
public class TestRepository {
    public String helloWorld() {
        return "Hello world Hotswap";
    }
}
