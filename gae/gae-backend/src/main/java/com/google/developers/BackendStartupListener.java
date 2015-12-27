package com.google.developers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;

public class BackendStartupListener extends GuiceServletContextListener {

	private static final Logger logger = LoggerFactory
			.getLogger(BackendStartupListener.class);

	private Injector injector;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		/*
		 * TODO switch to production
		 */
		injector = Guice.createInjector(Stage.DEVELOPMENT,
				new DevelopersSharedModule(),
				new DevelopersSharedServletModule(),
				new BackendServletModule());
		logger.info("created injector");

		super.contextInitialized(sce);

		return;
	}

	@Override
	protected Injector getInjector() {
		return injector;
	}

}
