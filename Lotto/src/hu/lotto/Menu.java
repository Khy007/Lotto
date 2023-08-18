package hu.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
	
	private String menuValaszto;
	private int huzasSzama;

	public int getHuzasSzama() {
		return huzasSzama;
	}

	public void setHuzasSzama(int huzasSzama) {
		this.huzasSzama = huzasSzama;
	}

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
			System.out.println("Válassz egy menüpontot:");
			System.out.println("1. Lottószámok húzása");
			System.out.println("2. Leggyakoribb 3 szám kigyűjtése");
			System.out.println("3. Tömb kiiratása");
			System.out.println("4. Kilépés");
		
				int valasztas = sc.nextInt();		
					switch(valasztas){
					case 1:						
						try {
							for (int i=0; i<huzasSzama; i++) {
								Huzas.szamHuzas();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}					
						break;
					case 2:
						int[] szamok1 = null;
						int[] leggyakoribbHarom = Kereses.findMostFrequent(szamok1);
		                System.out.println("A leggyakoribb három szám: " + Arrays.toString(leggyakoribbHarom));
		                System.out.println();
					  break;
					case 3:
						System.out.println("A tömb tartalma: " );
						for (int i = 0; i < Huzas.getKihuzottSzamokEddig().size(); i++) {
							System.out.print(String.format("%3d", Huzas.getKihuzottSzamokEddig().get(i)));
						    if ((i + 1) % 5 == 0) {
						        System.out.println();
						    }
						}
					  break;
					case 4:
					  System.out.println("Viszont látásra");
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

