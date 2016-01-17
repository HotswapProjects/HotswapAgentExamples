package org.hotswap.agent.example.deltaspike.appl;

import java.io.IOException;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.hotswap.agent.example.deltaspike.enums.UserRole;
import org.hotswap.agent.example.deltaspike.jpa.User;
import org.hotswap.agent.example.deltaspike.jpa.UserRepository;
import org.hotswap.agent.example.deltaspike.jsf.Messages;
import org.omnifaces.util.Faces;

@Named
@RequestScoped
public class LoginController {
    @Inject
    private LoginForm loginForm;

    @Inject
    private JsfMessage<Messages> messages;

    @Inject
    private transient UserBean userBean;

    @Inject
    private transient UserRepository userRepository;

    public void login() {
        if (doLogin(loginForm.getUserName(), loginForm.getPassword())) {
            messages.addInfo().loginOk();
            try {
                Faces.redirect(Faces.getContext().getExternalContext().getApplicationContextPath() + "/appl/home.jsf");
            } catch (IOException e) {
            }
        } else {
            messages.addError().loginFailed();
        }
    }

    public boolean doLogin(String userName, String password) {

        User user = userRepository.findOptionalByUserName(userName);

        if (user == null && "admin".equals(userName)) {
            user = new User();
            user.setUserName(userName);
            user.setFirstName("Admiral");
            user.setLastName("Admininovic");
            user.setEmail("admin@admin");
            user.setRole(UserRole.ADMIN);
            user.setUpdateDate(new Date());
            userRepository.save(user);
        }

        if (user != null) {
            userBean.setUser(user);
            userBean.setLogged(true);

            return true;
        } else {
        }
        return false;
    }

    public void logout() {
        userBean.logout();
        try {
            Faces.redirect(Faces.getContext().getExternalContext().getApplicationContextPath() + "/login.jsf");
        } catch (IOException e) {
        }
    }
}
