package com.google.developers;

import com.google.api.server.spi.SystemServiceServlet;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.google.training.helloworld.HelloWorldEndpoints;

import java.util.HashMap;

public class DefaultServletModule extends ServletModule implements Path {

	@Override
	protected void configureServlets() {

		/*
		 * /api/401/;jsessionid=37fycpy88nx7
		 */
		serve("/api/401/*").with(UnauthorizedServlet.class);

		/*
		 * A servlet is a singleton, and is allowed to be registered only once.
		 */

		{
			HashMap<String, String> params = new HashMap<>();

			/*
			 * comma delimited fqn of classes
			 */
			params.put("services", HelloWorldEndpoints.class.getCanonicalName());

			serve("/_ah/spi/*").with(SystemServiceServlet.class, params);
			bind(SystemServiceServlet.class).in(Singleton.class);
		}
	}
}
