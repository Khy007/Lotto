package hu.lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Huzas {
	
	static ArrayList<Integer> kihuzottSzamokEddig = new ArrayList<>();
	
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
	
}
