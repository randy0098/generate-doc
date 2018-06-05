package com.randy.test;

import java.util.ServiceLoader;

public class SpiMain {

	public static void main(String[] args) {
		ServiceLoader<HelloInterface> loaders = ServiceLoader.load(HelloInterface.class);
		for (HelloInterface in : loaders) {
			in.sayHello();
		}
	}

}
