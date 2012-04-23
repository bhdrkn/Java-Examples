package com.bahadirakin.rest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class App {

	public static void main(String[] args) {
		App app = new App();
		System.out.println("REST CLIENT APPLICATION STARTED");
		System.out.println("HTTP GET REQUEST IS GOING TO SEND...");
		String result = app
				.connectRestService("http://wicket.bahadirakin.com/rest/test");
		System.out.println("RESULT: " + result);
		System.out.println("END OF LINE");

	}

	public String connectRestService(String url) {
		String result = "";
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		ResponseHandler<String> handler = new BasicResponseHandler();
		try {
			result = httpClient.execute(request, handler);
		} catch (ClientProtocolException e) {
			System.err
					.println("An Exception occured while connection Rest web service... ClientProtocolException... M: "
							+ e.getMessage() + " C:" + e.getCause());
		} catch (IOException e) {
			System.err
					.println("An Exception occured while connection Rest web service... IOException... M: "
							+ e.getMessage() + " C:" + e.getCause());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
}
