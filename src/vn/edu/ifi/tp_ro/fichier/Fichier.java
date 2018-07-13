package vn.edu.ifi.tp_ro.fichier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Fichier {
	
	private static  String nameFichier ; 	
	
	public Fichier(String filepath) {
		Fichier.nameFichier = filepath ; 
	}
	
	public Fichier() {
		
	}
	
	public int[][] readFichier() {
		int[][] listNoeud = null ; 
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			
			fr = new FileReader(Fichier.nameFichier);
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			int indicate = 0; 
			int tableSize = 0 ; 
			System.out.println("------------------");
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				if(indicate == 0) { // noeud source 
					tableSize = Integer.parseInt(sCurrentLine); 
					listNoeud = new int[2*tableSize+2][tableSize];
					int[] noeuds = new int[tableSize]; 
			 		for(int i = 0 ; i< tableSize; i++) {
						noeuds[i] = i+1; 
					}
					listNoeud[indicate] = noeuds ; 
				}else { // reste de noeud 
					int[] noeuds = new int[tableSize]; 
					String[] nameNoeud = sCurrentLine.split(" "); 
					for(int i = 0 ; i< nameNoeud.length; i++) {
						noeuds[i] = Integer.parseInt(nameNoeud[i])+tableSize; 
					}
					listNoeud[indicate] = noeuds ; 
				}
				indicate = indicate+1; 
			}
			System.out.println("----------"+listNoeud.length+"-------------");
			int[] noeuds = new int[tableSize]; 
			listNoeud[listNoeud.length -1] = noeuds;
			for(int i= indicate ; i< listNoeud.length - 1 ; i++) {
				int[] n = {listNoeud.length -1,0,0};
				listNoeud[i] = n;
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		for(int i = 0 ; i < listNoeud.length; i++) {
			System.out.print(i+"--");
			for(int j = 0; j< listNoeud[i].length;j++) {
				System.out.print(listNoeud[i][j]);
			}
			System.out.println(" ");
		}
		
		return listNoeud ; 
	}

}
