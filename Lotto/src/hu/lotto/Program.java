package hu.lotto;

import java.sql.*;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Program {

	static Resource resource= new ClassPathResource("applicationContext.xml");
	static BeanFactory factory = new XmlBeanFactory(resource);
	
	public static void main(String[] args) {
		
		
		System.out.println("Valassz egy menupontot:");
		System.out.println("1. Lottoszamok huzasa");
		System.out.println("2. Leggyakoribb 3 szam kigyujtese");
		System.out.println("3. Tomb kiiratasa");
		System.out.println("4. EXIT");
		
		Menu menu =(Menu)factory.getBean("menu");
		menu.proc();

	}

}
