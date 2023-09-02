package hu.lotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Huzas {
	
	static String szerverC = new String("jdbc:mysql://192.168.100.10:3306/lotto_db");
	static String szerverF = new String("PallasBazsi");
	static String szerverJL = new String("Pallas2022*");
	
	static ArrayList<Integer> kihuzottSzamokEddig = new ArrayList<>();
	private int huzasSzama;

	public int getHuzasSzama() {
		return huzasSzama;
	}

	public void setHuzasSzama(int huzasSzama) {
		this.huzasSzama = huzasSzama;
	}
	
	public static ArrayList<Integer> getKihuzottSzamokEddig() {
		return kihuzottSzamokEddig;
	}

	public static void setKihuzottSzamokEddig(ArrayList<Integer> kihuzottSzamokEddig) {
		Huzas.kihuzottSzamokEddig = kihuzottSzamokEddig;
		
	}

	public static int[] szamHuzas() {
	    Set<Integer> lottoSzamok = new HashSet<>();
	    Random random = new Random();
	    
	    while (lottoSzamok.size() < 5) {
	        int kihuzottSzam = random.nextInt(90) + 1;
	        lottoSzamok.add(kihuzottSzam);
	        
	    }
	    int[] eredmeny = new int[5];
	    int i = 0;
	    for (int szam : lottoSzamok) {
	        eredmeny[i] = szam;
	        Huzas.kihuzottSzamokEddig.add(Integer.valueOf(szam));
	        i++;
	    }
	    return eredmeny;
	}
	
	public void dbInject() {
		try {
			Connection dbconnect = DriverManager.getConnection(szerverC, szerverF, szerverJL);		
			System.out.println("SQL szerver CONNECTED!:)");
			
			PreparedStatement preparedStatement = dbconnect.prepareStatement("INSERT INTO otosLotto VALUES(?, ?, ?, ?, ?, ?)");
			for (int i=0; i<huzasSzama; i++) {				
				preparedStatement.setString(1, String.valueOf(i+1));
				int s = 0;
					for (int j=i*5 ; j<i*5+5; j++) {
						preparedStatement.setString(s+2, Huzas.getKihuzottSzamokEddig().get(j).toString());	
						s++;
					}
					preparedStatement.executeUpdate();
			}
			preparedStatement.close();
			
		}catch (SQLException e) {
			System.out.println("Csatlakozás nem pipa :(");
		}
	}
	
	
	public void proc() {
		try {
			for (int i=0; i<huzasSzama; i++) {
				Huzas.szamHuzas();
			}
			dbInject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
