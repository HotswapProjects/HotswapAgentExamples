package org.hotswap.agent.example.deltaspike.secur;

import javax.enterprise.inject.Stereotype;

import org.apache.deltaspike.security.api.authorization.Secured;

@Stereotype
@Secured(AdminDecisionVoter.class)
public @interface AdminAllowed {
}