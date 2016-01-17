package org.hotswap.agent.example.deltaspike.secur;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.deltaspike.security.api.authorization.AbstractAccessDecisionVoter;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;
import org.hotswap.agent.example.deltaspike.appl.UserBean;

@ApplicationScoped
public class LoggedInDecisionVouter extends AbstractAccessDecisionVoter {

    private static final long serialVersionUID = 2876899181221759221L;

    @Inject
    private UserBean userBean;

    @Override
    protected void checkPermission(AccessDecisionVoterContext voterContext, Set<SecurityViolation> violations) {
        if (userBean.isLogged()) {
            return;
        }
        violations.add(newSecurityViolation("Access not allowed."));
    }
}
