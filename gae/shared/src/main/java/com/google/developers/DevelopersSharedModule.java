package com.google.developers;

import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DevelopersSharedModule implements Module {

	public static final String REFRESH_TOKEN = getMessage("refreshToken");
	public static final String CLIENT_ID = getMessage("clientId");
	public static final String CLIENT_SECRET = getMessage("clientSecret");

	@Override
	public void configure(Binder binder) {

//		binder.bind(HttpTransport.class).toInstance(new UrlFetchTransport());
		binder.bind(HttpTransport.class).toInstance(new NetHttpTransport());

		/*
		 * TODO HH?
		 */
		binder.bind(DateFormat.class).toInstance(
				new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"));

		binder.bind(JsonFactory.class).toInstance(
				JacksonFactory.getDefaultInstance());

		/*
		 * Global instance of the {@link DataStoreFactory}. The best practice is
		 * to make it a single globally shared instance across your application.
		 */
		binder.bind(DataStoreFactory.class).toInstance(
				AppEngineDataStoreFactory.getDefaultInstance());
		binder.bind(AppEngineDataStoreFactory.class).in(Singleton.class);
	}

	public static String getMessage(String key) {
		/*
		 * http://docs.oracle.com/javase/tutorial/i18n/intro/steps.html
		 */
		ResourceBundle bundle = ResourceBundle.getBundle("message");
		return bundle.getString(key);
	}
}
