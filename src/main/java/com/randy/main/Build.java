package com.randy.main;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.parser.SwaggerParser;

public class Build {
	public static void main(String[] args) {
		Swagger swagger = new SwaggerParser().read("http://localhost:8091/v2/api-docs");
		List<Tag> tags = swagger.getTags();
		Iterator<Tag> tagIte = tags.iterator();
		while(tagIte.hasNext()) {
			Tag tag = tagIte.next();
			String name = tag.getName();
			if(name!=null && name.equals("人员")) {
			}else {
				tagIte.remove();
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
				if(operationTags!=null && operationTags.contains("人员")) {
				}else {
					opIte.remove();
				}
			}
			
			System.out.println("path:" + path);
			System.out.println("------------------");
		}
		
		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder() 
		        .withMarkupLanguage(MarkupLanguage.ASCIIDOC) 
		        .withOutputLanguage(Language.ZH) 
		        .withPathsGroupedBy(GroupBy.TAGS) 
		        .build(); 
		java.nio.file.Path outputFile = Paths.get("target/generated-docs/asciidoc/all");
		Swagger2MarkupConverter.from(swagger) 
		.withConfig(config)
        .build() 
        .toFile(outputFile); 
	}
}
