package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class New {
	public static Properties prop;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		prop = new Properties();
		String s = System.getProperty("user.dir")+ "/src/main/java/com/qa/base/jatin.properties";
		prop.load(new FileInputStream(s));
		System.out.println(prop.getProperty("UserName"));
		System.out.println(prop.getProperty("Password"));

	}

}
