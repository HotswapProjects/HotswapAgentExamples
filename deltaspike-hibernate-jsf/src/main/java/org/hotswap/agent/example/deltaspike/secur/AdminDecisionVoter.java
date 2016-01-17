package org.hotswap.agent.example.deltaspike.secur;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.deltaspike.security.api.authorization.AbstractAccessDecisionVoter;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;
import org.hotswap.agent.example.deltaspike.appl.UserBean;
import org.hotswap.agent.example.deltaspike.enums.UserRole;

@ApplicationScoped
public class AdminDecisionVoter extends AbstractAccessDecisionVoter {

    private static final long serialVersionUID = -5211574141642639764L;

    @Inject
    private UserBean userBean;

    @Override
    protected void checkPermission(AccessDecisionVoterContext voterContext, Set<SecurityViolation> violations) {
        if (userBean.isLogged() && userBean.hasRole(UserRole.ADMIN)) {
            return;
        }
        violations.add(newSecurityViolation("Access not allowed."));
    }
}