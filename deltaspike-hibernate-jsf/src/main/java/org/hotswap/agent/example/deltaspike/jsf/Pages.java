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

    @Secured(LoggedInDecisionVouter.class)
    public interface Appl extends Pages {
        class Home implements Appl {};

        @Secured(AdminDecisionVoter.class)
        class Users implements Appl {};
    }

    public interface Errorpages extends Pages {
        class Error extends DefaultErrorView { }
        class LowPriviliges implements Errorpages {}
        class NotFound implements Errorpages {}
        class ViewExpired implements Errorpages {}
    }

}
