package org.hotswap.agent.example.deltaspike.appl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.hotswap.agent.example.deltaspike.enums.UserRole;
import org.hotswap.agent.example.deltaspike.jpa.User;

@Named
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 8926661793529450929L;

    private boolean logged = false;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void logout() {
        setLogged(false);
        setUser(null);
    }

    public boolean hasRole(UserRole role) {
        if (user != null && role != null) {
            return role == user.getRole();
        }
        return false;
    }
}
