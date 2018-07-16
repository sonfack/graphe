package vn.edu.ifi.tp_ro.utilisation;

import java.util.LinkedList;
import java.util.Scanner;

import vn.edu.ifi.tp_ro.couplage.Couplage;
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
			 Utilisation.graphBi = Couplage.creeReseau(fichier);
			 break ; 
		 }
		 default : {
			 System.out.println("Votre choix n est pas bon");
		 }
		 
		 }
		
	}
	public static void main(String[] args) {
		
		/*Noeud N0 = new Noeud(0)	;
		N0.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N1 = new Noeud(1)	;
		N1.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N2  = new Noeud(2); 
		N2.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N3  = new Noeud(3); 
		N3.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N4  = new Noeud(4); 
		N4.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N5  = new Noeud(5); 
		N5.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N6 = new Noeud(6)	;
		N6.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N8 = new Noeud(8)	;
		N8.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N7 = new Noeud(7)	;
		N7.afficheNoeud();
		System.out.println("-------------------------------------");
		N0.ajouteVoisin(N1, 1); 
		N0.afficheNoeud();
		System.out.println("-------------------------------------");
		N0.ajouteVoisin(N4, 1); 
		N0.afficheNoeud();
		System.out.println("-------------------------------------");
		N0.ajouteVoisin(N2, 1); 
		N0.afficheNoeud();
		System.out.println("-------------------------------------");
		N1.ajouteVoisin(N2, 1); 
		N1.afficheNoeud();
		System.out.println("-------------------------------------");
		N1.ajouteVoisin(N3, 1); 
		N1.afficheNoeud();
		System.out.println("-------------------------------------");
		N4.ajouteVoisin(N1, 1); 
		N4.afficheNoeud();
		System.out.println("-------------------------------------");
		N2.ajouteVoisin(N4, 1); 
		N2.afficheNoeud();
		System.out.println("-------------------------------------");
		N2.ajouteVoisin(N5, 1); 
		N2.afficheNoeud();
		System.out.println("-------------------------------------");
		N2.ajouteVoisin(N3, 1); 
		N2.afficheNoeud();
		System.out.println("-------------------------------------");
		N3.ajouteVoisin(N6, 1); 
		N3.afficheNoeud();
		System.out.println("-------------------------------------");
		N3.ajouteVoisin(N7, 1); 
		N3.afficheNoeud();
		System.out.println("-------------------------------------");
		N5.ajouteVoisin(N6, 1); 
		N5.afficheNoeud();
		System.out.println("-------------------------------------");
		N6.ajouteVoisin(N7, 1); 
		N6.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud[] TabNoeud = {N0, N1, N2, N3, N4, N5, N6, N7}; 
		Graphe g = new Graphe(TabNoeud); 
		LinkedList<Noeud> chemin = new LinkedList<Noeud>();
		chemin.add(N0);
		chemin.add(N1);
		chemin.add(N2);
		chemin.add(N3);
		chemin.add(N4);
		for(int i = 0;i<chemin.size();i++) {
			System.out.print("\n "+chemin.get(i).getId()+" : ");
			for(int j =0; j < chemin.get(i).getNbVoisins();j++) {
				System.out.print(chemin.get(i).getSuccesseurs().get(j).getId());
			}
			System.out.println(" ");
		}
		System.out.println(" -------------------inverse-----------------------");
		if(g.inverseChemin(chemin)) {
			for(int i = 0;i<chemin.size();i++) {
				System.out.print("\n "+chemin.get(i).getId()+" : ");
				for(int j =0; j < chemin.get(i).getNbVoisins();j++) {
					System.out.print(chemin.get(i).getSuccesseurs().get(j).getId());
				}
				System.out.println(" ");
			}
		}*/
		
		Utilisation user  = new Utilisation(); 
		user.menu();
		Graphe.parcoursGraphe(Utilisation.graphBi);
		/*LinkedList<Noeud> c = Utilisation.graphBi.cheminBFS(Utilisation.graphBi.getNoeuds().get(0), Utilisation.graphBi.getNoeuds().get(7));
		for(int i = 0 ; i < c.size(); i++) {
			
			System.out.print("\n  le chemin "+c.get(i).getId()+" ");
		}*/
	}

}
