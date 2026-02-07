package org.hotswap.agent.example.deltaspike.forms;

import jakarta.enterprise.inject.Model;
import jakarta.validation.constraints.NotEmpty;


@Model
public class LoginForm {

    @NotEmpty
    private String userName = "admin";

    @NotEmpty
    private String password = "admin";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
