package vn.edu.ifi.tp_ro.utilisation;

import java.util.LinkedList;
import java.util.Scanner;

import vn.edu.ifi.tp_ro.fichier.Fichier;
import vn.edu.ifi.tp_ro.graphe.Graphe;
import vn.edu.ifi.tp_ro.noeud.Noeud;

public class Utilisation {
	public static Graphe graphBi = new Graphe();  

	public void menu() {
		Scanner sc=new Scanner(System.in);  
		System.out.println("##################### MENU ####################");
		System.out.println("#### 1 Creer graph a partir du fichier"); 
		
		System.out.print("Entrez votre choix : ");
		 int choix =sc.nextInt();  
		 
		 switch(choix) {
		 case 1: {
			 String fichier = "/home/sonfack/Documents/tache.txt";
			 Utilisation.graphBi = Graphe.creeGraphe(fichier);
			 break ; 
		 }
		 default : {
			 System.out.println("Votre choix n est pas bon");
		 }
		 
		 }
		
	}
	public static void main(String[] args) {
		
		Utilisation user  = new Utilisation(); 
		user.menu();
		Graphe.parcoursGraphe(Utilisation.graphBi);
		LinkedList<Noeud> c = Utilisation.graphBi.cheminBFS(Utilisation.graphBi.getNoeuds().get(0), Utilisation.graphBi.getNoeuds().get(7));
		for(int i = 0 ; i < c.size(); i++) {
			
			System.out.print("\n  le chemin "+c.get(i).getId()+" ");
		}
	}

}
