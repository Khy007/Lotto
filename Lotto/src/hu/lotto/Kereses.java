package hu.lotto;

import java.util.HashMap;
import java.util.Map.Entry;

public class Kereses {
	
	
	public static int[] findMostFrequent(int[] szamHuzasok) {
	    
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
        int[] result = new int[3];

        // Egy ciklus, ami 3-szor ismétlődik
        for (int i = 0; i < 3; i++) {
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

}

