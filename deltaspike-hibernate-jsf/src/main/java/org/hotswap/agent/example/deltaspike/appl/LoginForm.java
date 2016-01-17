package org.hotswap.agent.example.deltaspike.appl;

import javax.enterprise.inject.Model;

import org.hibernate.validator.constraints.NotEmpty;

@Model
public class LoginForm {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

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
