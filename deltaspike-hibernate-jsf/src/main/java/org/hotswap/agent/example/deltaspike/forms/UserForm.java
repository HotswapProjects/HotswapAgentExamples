package org.hotswap.agent.example.deltaspike.forms;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import org.hotswap.agent.example.deltaspike.enums.UserRole;

@Model
public class UserForm implements Serializable {

    private static final long serialVersionUID = 1243968054551528996L;

    private Long userId;

    private String userName;

    private String firstName;

    private String lastName;

    private UserRole role = UserRole.USER;

    private boolean active;

    private String email;

    public boolean isNew() {
        return userId == null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
