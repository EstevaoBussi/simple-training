package com.simple.training.domain.json.support;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractJSONTestSupport {
	
	protected String getJSON(String uri) throws URISyntaxException, IOException {
		File file = new File(getClass().getResource(uri).toURI());
		return FileUtils.readFileToString(file, "UTF-8"); 
	}
	
	protected void compareJson(String uri, Object object) throws URISyntaxException, IOException{
		String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
		assertEquals(normalizeJSON(getJSON(uri)), normalizeJSON(json));
	}
	
	private String normalizeJSON(String json){
		return json.replace(" ", "").replace("\n", "");
	}
}
