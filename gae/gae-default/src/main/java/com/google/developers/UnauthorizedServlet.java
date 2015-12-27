package com.google.developers;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class UnauthorizedServlet extends HttpServlet {

	private final JsonFactory jsonFactory;

	@Inject
	public UnauthorizedServlet(JsonFactory jsonFactory) {
		this.jsonFactory = jsonFactory;
	}

	protected void unauthorized(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String userAgent = req.getHeader("User-Agent");

		resp.setHeader("Content-Type", "text/javascript");
		resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		JsonGenerator generator = jsonFactory.createJsonGenerator(resp.getWriter());
		generator.writeStartObject();
		generator.writeFieldName("error");
		generator.writeString("Login required.");
		generator.writeFieldName("q");
		generator.writeString(req.getQueryString());
		generator.writeFieldName("agent");
		generator.writeString(userAgent);
		generator.writeEndObject();
		generator.flush();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		unauthorized(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		unauthorized(req, resp);
	}
}