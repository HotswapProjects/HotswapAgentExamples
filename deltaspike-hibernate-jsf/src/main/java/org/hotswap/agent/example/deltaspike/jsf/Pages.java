package org.hotswap.agent.example.deltaspike.jsf;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;
import org.apache.deltaspike.jsf.api.config.view.View.NavigationMode;

@Folder(name="/")
public interface Pages extends ViewConfig {

    class Index implements ViewConfig {}

    @View(navigation = NavigationMode.REDIRECT)
    public interface Appl extends Pages {

        class Beans implements Appl {};
        class Users implements Appl {}

    }
}
