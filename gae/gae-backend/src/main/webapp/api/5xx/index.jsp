<%@ page language="java" trimDirectiveWhitespaces="true"
	contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"
	isErrorPage="true"
	import="com.google.inject.Injector,com.google.api.client.json.JsonFactory,com.google.api.client.json.JsonGenerator"%>
<%
	Injector injector = (Injector) application
			.getAttribute("com.google.inject.Injector");
	String m = pageContext.getException().getLocalizedMessage();
	JsonFactory jsonFactory = injector.getInstance(JsonFactory.class);
	JsonGenerator generator = jsonFactory.createJsonGenerator(out);
	generator.writeStartObject();
	generator.writeFieldName("error");
	generator.writeString(m);
	generator.writeEndObject();
	generator.flush();
%>