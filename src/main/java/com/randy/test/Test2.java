package com.randy.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;


public class Test2 {

	public static void main(String[] args) throws MalformedURLException, ConfigurationException {
		Configurations configs = new Configurations();
		Configuration configuration = configs.properties("config.properties"); 
		
		URL remoteSwaggerFile = new URL("http://petstore.swagger.io/v2/swagger.json");
		Path outputDirectory = Paths.get("build/asciidoc");

		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder() 
		        .withMarkupLanguage(MarkupLanguage.MARKDOWN) 
		        .withOutputLanguage(Language.DE) 
		        .withPathsGroupedBy(GroupBy.TAGS) 
		        .build(); 
		
		Swagger2MarkupConverter.from(remoteSwaggerFile) 
		        .build() 
		        .toFolder(outputDirectory); 
		
		
	}

}
