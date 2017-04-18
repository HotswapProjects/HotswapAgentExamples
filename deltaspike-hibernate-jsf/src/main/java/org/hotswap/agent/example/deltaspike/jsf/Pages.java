package org.hotswap.agent.example.deltaspike.jsf;

import org.apache.deltaspike.core.api.config.view.DefaultErrorView;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.security.api.authorization.Secured;
import org.hotswap.agent.example.deltaspike.secur.AdminDecisionVoter;
import org.hotswap.agent.example.deltaspike.secur.LoggedInDecisionVouter;

@Folder(name="/")
public interface Pages extends ViewConfig {

    class Login implements ViewConfig {}

//    class Login1 implements ViewConfig {}
//
//    class Login2 implements ViewConfig {}

}
