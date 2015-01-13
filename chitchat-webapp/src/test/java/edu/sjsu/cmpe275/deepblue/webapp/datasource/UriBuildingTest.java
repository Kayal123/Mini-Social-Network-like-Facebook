package edu.sjsu.cmpe275.deepblue.webapp.datasource;

import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;

public class UriBuildingTest {

	private static final String SCHEME = "http";
	private static final String HOST = "localhost:8080";
	private static final String RESOURCE = "/restapi";
	
	
	private URIBuilder uriBuilder;
	
	@Before
	public void setup() {
		uriBuilder = new URIBuilder().setScheme(SCHEME).setHost(HOST).setPath(RESOURCE);
	}
	
	@Test
	public void test() {
		assertTrue(true);
		
		System.out.println(uriBuilder.toString());
		
	}

}
