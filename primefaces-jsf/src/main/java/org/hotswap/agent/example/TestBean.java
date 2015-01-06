package org.hotswap.agent.example;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "testBean")
public class TestBean {

    private String value = "test";

    private String value2 = "test2";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}