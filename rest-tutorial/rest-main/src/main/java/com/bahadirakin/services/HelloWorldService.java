package com.bahadirakin.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
public class HelloWorldService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String saySimpleHello() {
		return "Hello, World!";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/{to}")
	public String sayXmlHello(@PathParam("to") String to) {
		return "<?xml version=\"1.0\"?><say>Hello, " + to + "!</say>";
	}

	@GET	
	@Produces(MediaType.TEXT_HTML)
	@Path("/query")
	public Response sayHelloAgain(@QueryParam("to") String to) {
		return Response.ok("Hello, " + to + "!", MediaType.TEXT_HTML).build();
	}

}
