package com.randy.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.configuration2.ex.ConfigurationException;

import com.randy.test.MySwaggerModelExtension;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.Swagger2MarkupExtensionRegistry;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.builder.Swagger2MarkupExtensionRegistryBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.parser.SwaggerParser;

public class Build {
	public static void main(String[] args) throws ConfigurationException, IOException {
		Properties properties = new Properties();
	    BufferedReader bufferedReader = new BufferedReader(new FileReader("config.properties"));
	    properties.load(bufferedReader);
	    String tags = properties.getProperty("tags");
	    String[] array = tags.split(",");
	    List<String> list = Arrays.asList(array);
	    System.out.println(tags);
		
		Swagger swagger = new SwaggerParser().read("http://localhost:8091/v2/api-docs");
		List<Tag> swTags = swagger.getTags();
		Iterator<Tag> tagIte = swTags.iterator();
		while(tagIte.hasNext()) {
			Tag tag = tagIte.next();
			String name = tag.getName();
			if(name!=null && list.contains(name)) {
			}else {
//				tagIte.remove();
			}
		}
		
		Map<String,Model> definitions = swagger.getDefinitions();
		Map<String,Path> paths = swagger.getPaths();
		Iterator ite = paths.entrySet().iterator();
		while(ite.hasNext()) {
			Map.Entry entry = (Entry) ite.next();
			String key = (String) entry.getKey();
			System.out.println("key:" + key);
			Path path = (Path) entry.getValue();
			List<Operation> operations = path.getOperations();
			Iterator<Operation> opIte = operations.iterator();
			while(opIte.hasNext()) {
				Operation operation = opIte.next();
				List<String> operationTags = operation.getTags();
				
				boolean flag = false;
				for(String str:operationTags) {
					if(list.contains(str)) {
						flag = true;
						break;
					}
				}
				
				if(!flag) {
//					opIte.remove();
				}
			}
			
			System.out.println("path:" + path);
			System.out.println("------------------");
		}
		
		Swagger2MarkupExtensionRegistry registry = new Swagger2MarkupExtensionRegistryBuilder() 
				.withSwaggerModelExtension(new MySwaggerModelExtension())
		        .build(); 
		
		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder() 
		        .withMarkupLanguage(MarkupLanguage.ASCIIDOC) 
		        .withOutputLanguage(Language.ZH) 
		        .withPathsGroupedBy(GroupBy.TAGS) 
		        .build(); 
		java.nio.file.Path outputFile = Paths.get("target/generated-docs/asciidoc/all");
		
		Swagger2MarkupConverter.from(swagger) 
		.withExtensionRegistry(registry) 
		.withConfig(config)
        .build() 
        .toFile(outputFile); 
	}
}
