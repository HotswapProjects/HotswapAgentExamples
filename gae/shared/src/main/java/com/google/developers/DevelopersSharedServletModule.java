package com.google.developers;

import com.google.inject.servlet.ServletModule;

public class DevelopersSharedServletModule extends ServletModule {

	@Override
	protected void configureServlets() {

		/*
		 * TODO /_ah/stop (not documented?)
		 */
		serve("/_ah/start").with(ModuleStartServlet.class);

//		serve("/appstats/*").with(AppstatsServlet.class);
//		bind(AppstatsServlet.class).in(Singleton.class);

		/*
		 * url handlers in non-default module require a workaround
		 * https://code.google.com/p/googleappengine/issues/detail?id=9859
		 */

		// filter("/*").through(AppstatsFilter.class);
//		filter("/*").through(ObjectifyFilter.class);
//		bind(ObjectifyFilter.class).in(Singleton.class);

		return;
	}

}
