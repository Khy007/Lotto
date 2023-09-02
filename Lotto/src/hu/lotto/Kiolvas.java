package hu.lotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kiolvas {
	private int mennyiAdat;

	public int getMennyiAdat() {
		return mennyiAdat;
	}

	public void setMennyiAdat(int mennyiAdat) {
		this.mennyiAdat = mennyiAdat;
	}
	
	public void dbCheck(int mennyiAdat) {
		try {
			Connection dbconnect = DriverManager.getConnection(Huzas.szerverC, Huzas.szerverF, Huzas.szerverJL);		
			System.out.println("SQL szerver CONNECTED!:)");
			
			PreparedStatement preparedStatement = dbconnect.prepareStatement("SELECT * FROM otosLotto LIMIT " + Integer.toString(mennyiAdat));
			ResultSet resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String aHuzas = resultset.getString("ID");
				String aSzam1 = resultset.getString("szam1");
				String aSzam2 = resultset.getString("szam2");
				String aSzam3 = resultset.getString("szam3");
				String aSzam4 = resultset.getString("szam4");
				String aSzam5 = resultset.getString("szam5");				
				System.out.println("Húzás: "+aHuzas+" számok> " +aSzam1+ ", "+aSzam2+ ", "+aSzam3+ ", "+aSzam4+ ", "+aSzam5);
			}
			preparedStatement.close();
			
		}catch (SQLException e) {
			System.out.println("Csatlakozás nem pipa :(");
		}
		
	}
	
	public void proc() {
		dbCheck(mennyiAdat);
	}
	
	
}
