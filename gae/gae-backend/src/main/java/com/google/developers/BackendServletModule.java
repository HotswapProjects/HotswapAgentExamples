package com.google.developers;

import com.google.inject.servlet.ServletModule;

public class BackendServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/cron/long-task").with(BackendServlet.class);
	}

}
