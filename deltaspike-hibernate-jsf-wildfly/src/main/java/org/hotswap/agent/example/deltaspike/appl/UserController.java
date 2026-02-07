package org.hotswap.agent.example.deltaspike.appl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewRef;
import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.hotswap.agent.example.deltaspike.forms.UserForm;
import org.hotswap.agent.example.deltaspike.jpa.User;
import org.hotswap.agent.example.deltaspike.jpa.UserRepository;
import org.hotswap.agent.example.deltaspike.jsf.Messages;
import org.hotswap.agent.example.deltaspike.jsf.Pages;
import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
@ViewRef(config=Pages.Appl.Users.class)
public class UserController extends MasterDetailControllerBase<User> implements Serializable {

    private static final long serialVersionUID = -4538115105092134763L;

    @Inject
    private UserForm form;

    @Inject
    private UserRepository userRepository;

    @Inject
    private JsfMessage<Messages> messages;

    private List<User> users;

    public List<User> getUserList() {
        if (users == null) {
            List<User> allUsers = userRepository.findAll();
            if (allUsers != null) {
                users = allUsers.stream()
                    .sorted((u1, u2) -> u1.getUserName().compareTo(u2.getUserName()))
                    .collect(Collectors.toList());
            } else {
                users = Collections.emptyList();
            }
        }
        return users;
    }

    public boolean isNew() {
        return getDetail().getUserId() != null;
    }

    /**
     * Save user.
     */
    public void saveUser() {
        User user;

        if (getDetail() == null) {
            user = new User();
        } else {
            user = getDetail();
        }

        user.setUserName(form.getUserName());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setRole(form.getRole());
        user.setEmail(form.getEmail());
        user.setUpdateDate(new Date());

        try {
            user = userRepository.save(user);
            messages.addInfo().saveOK();
            users = null;
            setDetailLevel(LIST_LEVEL);
            clearDetail();
        } catch (Exception e) {
            messages.addError().saveFailed();
        }
    }

    public void deleteUser() {
        if (getDetail().getUserId() != null) {
            User user = userRepository.findBy(getDetail().getUserId());
            if (doDeleteUser(user)) {
                setDetailLevel(LIST_LEVEL);
                clearDetail();
            }
        }
    }

    public void deleteUser(User user) {
        doDeleteUser(user);
    }

    private boolean doDeleteUser(User user) {
        user = userRepository.findBy(user.getUserId());
        if (user != null) {
            try {
                userRepository.remove(user);
                messages.addInfo().deleteOK();
                users = null;
                return true;
            } catch (Exception e) {
            }
        }
        messages.addInfo().deleteFailed();
        return false;
    }

    @Override
    protected void onDetailSelected(User detail) {
        if (detail != null) {
            form.setUserId(detail.getUserId());
            form.setUserName(detail.getUserName());
            form.setFirstName(detail.getFirstName());
            form.setLastName(detail.getLastName());
            form.setRole(detail.getRole());
        }
    }

    public String getHello() {
        return "Hello World1!";
    }



}
