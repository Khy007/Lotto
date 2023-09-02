package hu.lotto;

import java.sql.*;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Program {

	public static void main(String[] args) {
		
	
		Resource resource= new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		System.out.println("Válassz egy menüpontot:");
		System.out.println("1. Lottószámok húzása");
		System.out.println("2. Leggyakoribb 3 szám kigyűjtése");
		System.out.println("3. Tömb kiiratása");
		System.out.println("4. Kilépés");
		
		Menu menu =(Menu)factory.getBean("menu");
		menu.proc();

	}

}
