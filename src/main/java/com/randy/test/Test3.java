package com.randy.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Test3 {

	public static void main(String[] args) throws ConfigurationException, IOException {
		// TODO Auto-generated method stub
//		Configurations configs = new Configurations();
//		Configuration configuration = configs.properties("config.properties"); 
//		System.out.println(configuration.getProperty("tags"));
		
//		ResourceBundle resource = ResourceBundle.getBundle("main/resources/config.properties");
//		String tags = resource.getString("tags");
//		System.out.println(tags);
		
		Properties properties = new Properties();
	    // 使用InPutStream流读取properties文件
	    BufferedReader bufferedReader = new BufferedReader(new FileReader("config.properties"));
	    properties.load(bufferedReader);
	    String tags = properties.getProperty("tags");
	    System.out.println(tags);
	}

}
