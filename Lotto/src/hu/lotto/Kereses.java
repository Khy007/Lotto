package hu.lotto;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Kereses {
	
	private int bestOf;
	
	public int getBestOf() {
		return bestOf;
	}

	public void setBestOf(int bestOf) {
		this.bestOf = bestOf;
	}
	
	public void dbSearch() {
		try {
			Connection dbconnect = DriverManager.getConnection(Huzas.szerverC, Huzas.szerverF, Huzas.szerverJL);		
			System.out.println("SQL szerver CONNECTED!:)");
			
			PreparedStatement preparedStatement = dbconnect.prepareStatement("SELECT number, COUNT(*) AS count FROM "
					+ "(SELECT szam1 AS number FROM otosLotto "
					+ "UNION ALL SELECT szam2 FROM otosLotto "
					+ "UNION ALL SELECT szam3 FROM otosLotto "
					+ "UNION ALL SELECT szam4 FROM otosLotto "
					+ "UNION ALL SELECT szam5 FROM otosLotto ) AS numbers "
					+ "GROUP BY number "
					+ "ORDER BY count "
					+ "DESC LIMIT " + Integer.toString(bestOf)+ ";");
			
			ResultSet resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String aLeggyakoribb = resultset.getString("number");
				String aElofordulas = resultset.getString("count");
				System.out.println("Szam: "+aLeggyakoribb+" ennyiszer fordul elo> " + aElofordulas);
			}
			preparedStatement.close();
			
		}catch (SQLException e) {
			System.out.println("Csatlakozás nem pipa :(");
		}
	}

	
	
	public static int[] findMostFrequent(int[] szamHuzasok, int bestOf) {
	    
		
		 // Egy HashMap, ami tárolja a számok előfordulási gyakoriságát
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : Huzas.getKihuzottSzamokEddig()) {
            // Ha a szám már szerepel a HashMapben, növeljük az értékét eggyel
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
            // Ha a szám még nem szerepel a HashMapben, adjuk hozzá eggyel az értékét
            else {
                map.put(num, 1);
            }
        }

        // Egy tömb, ami tárolja a 3 leggyakoribb számot
        int[] result = new int[bestOf];

        // Egy ciklus, ami 3-szor ismétlődik
        for (int i = 0; i < bestOf; i++) {
            // Egy változó, ami tárolja a legnagyobb gyakoriságú számot
            int max = 0;
            // Egy változó, ami tárolja a legnagyobb gyakoriságú szám kulcsát
            int key = 0;
            // Egy ciklus, ami végigmegy a HashMap minden elemén
            for (Entry<Integer, Integer> entry : map.entrySet()) {
                // Ha az aktuális elem értéke nagyobb, mint a max változó értéke
                if (entry.getValue() > max) {
                    // Frissítjük a max és a key változókat az aktuális elemmel
                    max = entry.getValue();
                    key = entry.getKey();
                }
            }
            // Hozzáadjuk a key változót a tömbhöz az i. helyre
            result[i] = key;
            // Töröljük a key változót a HashMapből, hogy ne vegyük figyelembe a következő iterációban
            map.remove(key);
        }
        
	    return result;
	}
	
	public void proc() {
		int[] szamok1 = null;
		int[] leggyakoribbHarom = Kereses.findMostFrequent(szamok1, bestOf);
        System.out.println("Java HasMap szerint: ");
		System.out.println(Arrays.toString(leggyakoribbHarom));
        System.out.println();
        
        //Eltérés lehet, ha a legtöbb "bestOf"-értékben vannak egyenlő számban előforduló értékek!
        
        System.out.println("Adatbázis szerint: ");
        dbSearch();
        System.out.println();
		
	}
	
}

