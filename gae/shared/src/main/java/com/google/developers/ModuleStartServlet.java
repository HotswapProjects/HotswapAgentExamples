package com.google.developers;

import com.google.appengine.api.LifecycleManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class ModuleStartServlet extends HttpServlet implements
		LifecycleManager.ShutdownHook {

	private static final Logger logger = LoggerFactory
			.getLogger(ModuleStartServlet.class);

	@Inject
	public ModuleStartServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		logger.info("module start");
		LifecycleManager.getInstance().setShutdownHook(this);
		// ModulesService modulesApi =
		// ModulesServiceFactory.getModulesService();
		// if ("0".equals(modulesApi.getCurrentInstanceId())) {
		//
		// logger.info("module start");
		// LifecycleManager.getInstance().setShutdownHook(this);
		//
		// }

		return;
	}

	@Override
	public void shutdown() {

		logger.info("module shutdown");
		// LifecycleManager.getInstance().interruptAllRequests();

		return;
	}

}
