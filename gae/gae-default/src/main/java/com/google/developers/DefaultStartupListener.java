package com.google.developers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;

/**
 * loader for the web application
 *
 * @author renfeng
 */
public class DefaultStartupListener extends GuiceServletContextListener {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultStartupListener.class);

	/*
	 * removed static keyword
	 */
	private Injector injector;

	/*
	 * XXX for deferred task - which i would gave up because of lack of logging
	 * output in eclipse junit, and unreadable payload on appengine.google.com
	 */

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		/*
		 * TODO switch to production
		 */
		logger.info("created injector");
		injector = Guice.createInjector(Stage.DEVELOPMENT,
				new DevelopersSharedModule(),
				new DevelopersSharedServletModule(),
				new DefaultServletModule());

		super.contextInitialized(sce);
	}

	@Override
	protected Injector getInjector() {
		return injector;
	}

}