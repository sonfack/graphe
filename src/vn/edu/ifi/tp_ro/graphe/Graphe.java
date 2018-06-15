package vn.edu.ifi.tp_ro.graphe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import vn.edu.ifi.tp_ro.noeud.Noeud;

public class Graphe {

	protected int nbNoeuds;
	protected ArrayList<Noeud> noeuds;
	
	public Graphe() {
		nbNoeuds = 0;
		noeuds = null;
	}
	
	public Graphe(Noeud[] nds) {
		nbNoeuds = nds.length;
		noeuds = new ArrayList<Noeud>(nbNoeuds);
		for (int i = 0; i < nbNoeuds; i++) {
			noeuds.add(i, nds[i]);
		}
	}
	
	public static ArrayList<Noeud> parcoursGraphe(Graphe g) {
		
		LinkedList<Noeud> Q = new LinkedList<Noeud>(); 
	
		ArrayList<Noeud> S = new ArrayList<Noeud>(); 
		if(g.nbNoeuds > 0) {
			Q.add(g.noeuds.get(0));   
			while(!Q.isEmpty()){
				Noeud v = Q.pop();  
				System.out.print("Noeude ID : "+v.getId()+" voisons :");
				for(int i = 0; i < v.getNbVoisins(); i++) {
					System.out.print(v.getSuccesseurs().get(i).getId()+" ");
					if(!Q.contains(v.getSuccesseurs().get(i)) && !S.contains(v.getSuccesseurs().get(i))) {
						Q.add(v.getSuccesseurs().get(i));
					}
				}
				System.out.println(" ");
				S.add(v); 
			}
			
		}
		System.out.println("les noeuds du graphe sont : ");
		for(int i = 0 ; i< S.size(); i++) {
			System.out.print(S.get(i).getId()+" ");
		}
		return S; 
	}
	
	public static void courtCheminGraphe(Graphe g, Noeud s, Noeud t) {
		Graphe sg ; 
		ArrayList sl = null ; 
		if(Graphe.rechercheGraphe(g, s) == 1 && Graphe.rechercheGraphe(g, t)==1) {
			int indexS = g.getIndexNoeud(s); 
			Noeud[] tabNoeud = new Noeud[g.noeuds.size() - indexS]; 
			for(int i = indexS ; i < tabNoeud.length ; i++) {
				tabNoeud[i] = g.noeuds.get(i); 
			}
			sg = new Graphe(tabNoeud); 
			sl = Graphe.SousRechercheGraphe(sg, t); 
			int taille  = sl.size() -1 ; 
			Noeud v = t; 
			System.out.print("\n-----Le court chemain de "+s.getId()+" à "+v.getId()+" est :"+ v.getId());
			while(taille > 0) {
				int i = 0;
				while(!sg.noeuds.get(i).getSuccesseurs().contains(v)) {
					//System.out.println("Ne contient pas "+v.getId());
					i=i+1; 
				}
				if(sg.noeuds.get(i).getSuccesseurs().contains(v)) {
					v = sg.noeuds.get(i); 
					System.out.print(" "+v.getId()+" ");
				}
				taille = i; 
			}
		}
	}
	
	// retourne l'index d un noeud dans la liste des noeud du graphe
	private int getIndexNoeud(Noeud s) {
		// TODO Auto-generated method stub
		int index = 0; 
		boolean trouver = false ; 
		while(index < this.noeuds.size() && !trouver) {
			if(this.noeuds.get(index).compareTo(s)!= 0) {
				index++;
			}else {
				trouver = true ;
			}
		}
		return index;
	}
	
	public static  ArrayList<Noeud> SousRechercheGraphe(Graphe g, Noeud t) {
		// la FIFO des noeuds a parcourir 
		LinkedList<Noeud> Q = new LinkedList<Noeud>(); 
		// liste de noeuds deja parcourus 
		ArrayList<Noeud> S = new ArrayList<Noeud>();
		int trouver = -1; 
		int indexNoeud = 0 ; 
		if(g.nbNoeuds > 0) {
			Q.add(g.noeuds.get(0));   
			while(!Q.isEmpty() && trouver == -1){
				Noeud v = Q.pop();
				if(v.compareTo(t) != 0) {
					System.out.println("Different :"+v.getId()+" "+t.getId());
					for(int i = 0; i < v.getNbVoisins(); i++) {
						if(!Q.contains(v.getSuccesseurs().get(i)) && !S.contains(v.getSuccesseurs().get(i))) {
							Q.add(v.getSuccesseurs().get(i));
						}
					}
					S.add(v); 
				}else {
					trouver = 1; 
				}
			}
			if(trouver == -1) {
				System.out.println("N existe pas ");
			}else {
				System.out.println("Exist");
				System.out.println("Egale: "+t.getId());
			}
			
		}
		System.out.println("les noeuds du graphe sont : ");
		for(int i = 0 ; i< S.size(); i++) {
			System.out.print(S.get(i).getId()+" ");
		}
		return S ; 
	}
	
	// retourne 1 si noeud existe
	// retourne -1 si noeud n existe pas 
	public static int rechercheGraphe(Graphe g, Noeud t) {
		// la FIFO des noeuds a parcourir 
		LinkedList<Noeud> Q = new LinkedList<Noeud>(); 
		// liste de noeuds deja parcourus 
		ArrayList<Noeud> S = new ArrayList<Noeud>();
		int trouver = -1; 
		int indexNoeud = 0 ; 
		if(g.nbNoeuds > 0) {
			Q.add(g.noeuds.get(0));   
			while(!Q.isEmpty() && trouver == -1){
				Noeud v = Q.pop();
				if(v.compareTo(t) != 0) {
					System.out.println("Different :"+v.getId()+" "+t.getId());
					for(int i = 0; i < v.getNbVoisins(); i++) {
						if(!Q.contains(v.getSuccesseurs().get(i)) && !S.contains(v.getSuccesseurs().get(i))) {
							Q.add(v.getSuccesseurs().get(i));
						}
					}
					S.add(v); 
				}else {
					trouver = 1; 
				}
			}
			if(trouver == -1) {
				System.out.println("N existe pas ");
			}else {
				System.out.println("Exist");
				System.out.println("Egale: "+t.getId());
			}
			
		}
		System.out.println("les noeuds du graphe sont : ");
		for(int i = 0 ; i< S.size(); i++) {
			System.out.print(S.get(i).getId()+" ");
		}
		return trouver ; 
	}
	
	public static void main(String[] args) {
		Noeud N0 = new Noeud(0)	;
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
		N4.afficheNoeud();
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
		N1.ajouteVoisin(N2, 1); 
		N1.afficheNoeud();
		System.out.println("-------------------------------------");
		N1.ajouteVoisin(N3, 1); 
		N1.afficheNoeud();
		System.out.println("-------------------------------------");
		N4.ajouteVoisin(N1, 1); 
		N1.afficheNoeud();
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
		System.out.println("-------------------------------------");
		System.out.println("Graphe : "+g.nbNoeuds);
		Graphe.parcoursGraphe(g);
		System.out.println("-------------------------------------");
		Graphe.courtCheminGraphe(g, N0, N6);
		System.out.println("\n------------------------------------");
		//Graphe.rechercheGraphe(g, N4);
		
		
	}

	
}
