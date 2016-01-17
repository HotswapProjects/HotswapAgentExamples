package org.hotswap.agent.example.deltaspike.appl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewRef;
import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.hotswap.agent.example.deltaspike.jpa.User;
import org.hotswap.agent.example.deltaspike.jpa.UserRepository;
import org.hotswap.agent.example.deltaspike.jsf.Messages;
import org.hotswap.agent.example.deltaspike.jsf.Pages;
import org.hotswap.agent.example.deltaspike.secur.AdminAllowed;
import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
@AdminAllowed
@ViewRef(config=Pages.Appl.Users.class)
public class UserController extends MasterDetailControllerBase<User> implements Serializable {

    private static final long serialVersionUID = 1113369438647690918L;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserForm userForm;

    @Inject
    private JsfMessage<Messages> messages;

    private List<User> users;

    public List<User> getUserList() {
        if (users == null) {
            users = userRepository.findAll();
        }
        return users;
    }

    public void saveUser() {
        User user;

        if (getDetail() == null) {
            user = new User();
            user.setUpdateDate(new Date());
        } else {
            user = getDetail();
        }

        user.setUserName(userForm.getUserName());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setRole(userForm.getRole());
        user.setUpdateDate(new Date());

        userRepository.save(user);
        messages.addInfo().saveOK();
        users = null;
        setDetailLevel(LIST_LEVEL);
        clearDetail();
    }

    public void deleteUser(User user) {
        userRepository.remove(user);
        messages.addInfo().deleteOK();
    }

    @Override
    protected void onDetailSelected(User detail) {
        if (detail != null) {
            userForm.setUserId(detail.getUserId());
            userForm.setUserName(detail.getUserName());
            userForm.setFirstName(detail.getFirstName());
            userForm.setLastName(detail.getLastName());
            userForm.setRole(detail.getRole());
        }
    }

}
