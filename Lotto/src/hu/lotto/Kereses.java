package hu.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kereses {
	
	public static int[] findMostFrequent(int[] szamHuzas) {
	    
		Map<Integer, Integer> frequencyMap = new HashMap<>();
	    for (int szam : szamHuzas) {
	        frequencyMap.put(szam, frequencyMap.getOrDefault(szam, 0) + 1);
	    }
	    List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
	    Collections.sort(entries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));
	    int[] result = new int[3];
	    for (int i = 0; i < 3; i++) {
	        result[i] = entries.get(i).getKey();
	    }
	    return result;
	}
}
