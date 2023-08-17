package hu.lotto;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Huzas {

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
	        i++;
	    }
	    return eredmeny;
	}
}
