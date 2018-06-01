package com.randy.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.github.swagger2markup.spi.SwaggerModelExtension;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

public class MySwaggerModelExtension extends SwaggerModelExtension{

	@Override
	public void apply(Swagger swagger) {
		List<Tag> tags = swagger.getTags();
		Iterator<Tag> tagIte = tags.iterator();
		while(tagIte.hasNext()) {
			Tag tag = tagIte.next();
			String name = tag.getName();
			if(name!=null && name.equals("用户")) {
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
				if(operationTags!=null && operationTags.contains("用户")) {
				}else {
					opIte.remove();
				}
			}
			
			System.out.println("path:" + path);
			System.out.println("------------------");
		}
	}
}
