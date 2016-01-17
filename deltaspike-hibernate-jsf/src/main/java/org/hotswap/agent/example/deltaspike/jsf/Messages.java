package org.hotswap.agent.example.deltaspike.jsf;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@MessageBundle
public interface Messages {

    @MessageTemplate("login.msg.loginOk")
    public String loginOk();

    @MessageTemplate("login.msg.loginFailed")
    public String loginFailed();

    @MessageTemplate("common.saveOK")
    public String saveOK();

    @MessageTemplate("common.saveFailed")
    public String saveFailed();

    @MessageTemplate("common.deleteOK")
    public String deleteOK();

    @MessageTemplate("common.deleteFailed")
    public String deleteFailed();
}
