package vn.edu.ifi.tp_ro.couplage;

import java.util.ArrayList;

import vn.edu.ifi.tp_ro.fichier.Fichier;
import vn.edu.ifi.tp_ro.graphe.Graphe;
import vn.edu.ifi.tp_ro.noeud.Noeud;
import vn.edu.ifi.tp_ro.utilisation.Utilisation;

public class Couplage {
	/**
	 * crée le réseau à flot correspondant au fichier passe en argument
	 * @param fichier
	 * @return
	 */
	public static Graphe creeReseau(String fichier) {
		System.out.println(" ");
		Graphe graphe;
		ArrayList<Integer> creeNoeud = new ArrayList<Integer>();
		if(!fichier.isEmpty()) {
			graphe = new Graphe();
			int [][] matrice = null ;
			Fichier taskfile = new Fichier(fichier); 
			matrice = taskfile.readFichier();
			System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm ");
			for(int i = 0 ; i < matrice.length; i++ ){
				System.out.print(" noeud "+i +" successeurs : ");
				for(int j = 0 ; j < matrice[i].length; j++) {
					System.out.print(matrice[i][j]+" ");
				}
				System.out.println(" ");
			}
			System.out.println(" mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
			int nbnoeud = 0;
			 while(nbnoeud < matrice.length){
					System.out.print(nbnoeud+"------");
					if(!creeNoeud.contains(nbnoeud)) {
						Noeud newNoeud = new Noeud(nbnoeud);
						Noeud sucNoeud ;
						for(int j = 0; j< matrice[nbnoeud].length;j++) {
							if(matrice[nbnoeud][j] != 0 && !creeNoeud.contains(matrice[nbnoeud][j])) {
								System.out.print(matrice[nbnoeud][j]);
								sucNoeud = new Noeud(matrice[nbnoeud][j]);
								creeNoeud.add(matrice[nbnoeud][j]);
								newNoeud.setSuccesseurs(sucNoeud, 1);
							}else if(creeNoeud.contains(matrice[nbnoeud][j])) {
								sucNoeud = graphe.rechercheGraphe(matrice[nbnoeud][j]);
								newNoeud.setSuccesseurs(sucNoeud, 1);
							}
						}
						graphe.setNoeuds(newNoeud);
					}else {
						Noeud newNoeud = graphe.rechercheGraphe(nbnoeud);
						Noeud sucNoeud ;
						for(int j = 0; j<  matrice[nbnoeud].length;j++) {
							if(matrice[nbnoeud][j] != 0 && !creeNoeud.contains(matrice[nbnoeud][j])) {
								System.out.print(matrice[nbnoeud][j]);
								sucNoeud = new Noeud(matrice[nbnoeud][j]);
								creeNoeud.add(matrice[nbnoeud][j]);
								newNoeud.setSuccesseurs(sucNoeud, 1);
							}else if(creeNoeud.contains(matrice[nbnoeud][j])){
								sucNoeud = graphe.rechercheGraphe(matrice[nbnoeud][j]);
								newNoeud.setSuccesseurs(sucNoeud, 1);
							}
						}
						graphe.setNoeuds(newNoeud);
					}
					
				nbnoeud = nbnoeud + 1;
			 }
		}else {
			graphe = null;
		}
		System.out.println(" avant return "+graphe.getNbNoeuds());

		return graphe ;
	}
	
	
	
	public static void main(String[] args) {
		 String fichier = "/home/sonfack/Documents/tache.txt";
		 Graphe graphe = Couplage.creeReseau(fichier);
		 Graphe.parcoursGraphe(graphe);
		
	}
	
	
}
