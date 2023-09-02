package hu.lotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Menu {
	
	Resource resource = new ClassPathResource("applicationContext.xml");
	BeanFactory factory = new XmlBeanFactory(resource);
	private String menuValaszto;
	

	public String getMenuValaszto() {
		return menuValaszto;
	}

	public void setMenuValaszto(String menuValaszto) {
		this.menuValaszto = menuValaszto;
	}
	
	public void proc() {
	if(menuValaszto.equals("sima")) {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
						
			
			
				int valasztas = sc.nextInt();		
				switch(valasztas){
				case 1:	
					Huzas huzas=(Huzas)factory.getBean("huzas");
					huzas.proc();		
					break;
				case 2:
					Kereses kereses=(Kereses)factory.getBean("kereses");
					kereses.proc();      
				  break;
				case 3:
					System.out.println("A tömb tartalma: " );
					Kiolvas kiolvas=(Kiolvas)factory.getBean("kiolvas");
					kiolvas.proc(); 
					
/*					for (int i = 0; i < Huzas.getKihuzottSzamokEddig().size(); i++) {
						System.out.print(String.format("%3d", Huzas.getKihuzottSzamokEddig().get(i)));
					    if ((i + 1) % 5 == 0) {
					        System.out.println();
					    }
					}
*/				  break;
				case 4:
				  System.out.println("Viszont latasra :) ");
				  
						try {
							Connection dbconnect = DriverManager.getConnection(Huzas.szerverC, Huzas.szerverF, Huzas.szerverJL);		
							System.out.println("SQL szerver CONNECTED!:)");
							
							PreparedStatement preparedStatement = dbconnect.prepareStatement("TRUNCATE TABLE otosLotto");
							System.out.println("Az adattábla kiürítve!");
							preparedStatement.executeUpdate();
							preparedStatement.close();
							
						}catch (SQLException e) {
							System.out.println("Csatlakozas nem pipa :(");
						}
					
				  exit = true;
				  break;
				default:
					System.out.println("Kérlek csak számokat használj!");
			    	break;
				}
			}
			
			}else {
				System.out.println("NEM LÉTEZŐ MENÜ OPCIÓ");
			}
	
	}

}

