package org.hotswap.agent.example.deltaspike.jsf;

import javax.enterprise.inject.Specializes;

import org.apache.deltaspike.jsf.api.config.JsfModuleConfig;
import org.apache.deltaspike.jsf.spi.scope.window.ClientWindowConfig;

@Specializes
public class AppJsfModuleConfig extends JsfModuleConfig {

    private static final long serialVersionUID = -3382887706461201688L;

    @Override
    public ClientWindowConfig.ClientWindowRenderMode getDefaultWindowMode() {
        return ClientWindowConfig.ClientWindowRenderMode.NONE;
    }
}