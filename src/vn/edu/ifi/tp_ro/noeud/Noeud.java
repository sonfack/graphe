package vn.edu.ifi.tp_ro.noeud;

import java.util.ArrayList;

public class Noeud  implements Comparable<Noeud>{
	protected int id ; 
	protected int nbVoisins;
	// c'est plus simple d'ajouter et d'enlever des elements avec une liste qu'avec un array
	protected ArrayList<Noeud>  successeurs;
	protected ArrayList<Integer> arcs; //arcs sortant 
	
	// cree un noeud isole
	public Noeud() {
		nbVoisins = 0;
		successeurs = null;
		arcs = null;
	}
	
	public Noeud(int ID) {
		this.id = ID; 
		nbVoisins = 0;
		successeurs = null;
		arcs = null;
	}
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getNbVoisins() {
		return nbVoisins;
	}



	public void setNbVoisins(int nbVoisins) {
		this.nbVoisins = nbVoisins;
	}



	public ArrayList<Noeud> getSuccesseurs() {
		return successeurs;
	}



	public void setSuccesseurs(ArrayList<Noeud> successeurs) {
		this.successeurs = successeurs;
	}



	public ArrayList<Integer> getArcs() {
		return arcs;
	}



	public void setArcs(ArrayList<Integer> arcs) {
		this.arcs = arcs;
	}



	// cree un noeud a partir d'une liste de voisins
	// les arcs sont de poids 1
	public Noeud(Noeud[] noeuds) {
		int n = noeuds.length;
		if (n == 0) {
			nbVoisins = 0;
			successeurs = null;
			arcs = null;
		}
		else {
			nbVoisins = n;
			successeurs = new ArrayList<Noeud>(n);
			arcs = new ArrayList<Integer>(n);
			for (int i = 0; i < n; i++) {
				successeurs.add(i, noeuds[i]);
				arcs.add(i, 1);
			}
		}
	}
	
	// renvoie le nombre de voisins
	public int nbVoisins() {
		return nbVoisins;
	}
	
	// renvoie le degre sortant, qui vaut la somme des elements de arcs
	public int degreSortant() {
		// a completer
		return nbArcs(this);
	}
	
	// renvoie l'indice de v dans voisins si v est voisin
	// renvoie -1 si v n'est pas voisin
	public int estVoisin(Noeud v) {
		int index =  0; 
		int compare = 1; 
		if(this.successeurs != null) {
			while(index < this.successeurs.size() && compare != 0){
				compare = this.successeurs.get(index).compareTo(v); 
				index = index+1; 
			}
			if(index >= this.successeurs.size()) {
				// a completer
				return -1;
			}else {
				// est voisin
				return index; 
			}
		}else {
			return -1; 
		}
	}
	
	// renvoie le poids de l'arc correspondant si v est voisin, 0 sinon
	public int nbArcs(Noeud v) {
		int nbArc = 0; 
		for(int i =0 ; i< v.arcs.size(); i++) {
			nbArc = nbArc + v.arcs.get(i) ; 
		}
		// a completer
		return nbArc;
	}
	
	
	// si v est deja voisin, modifie la poids de l'arc correspondant
	// sinon, ajoute v comme un nouveau voisin avec un arc de poids d
	// (il faut incrementer nbArcs si c'est un nouveau voisin)
	// revoie le nouveau nombre de voisins
	public int ajouteVoisin(Noeud v, int d) {
		// v est voisin 
		if(this.successeurs != null) {
			int indexV = this.estVoisin(v);  
			if(indexV != -1) {
				this.successeurs.add(v); 
				this.arcs.add(d);
				this.nbVoisins = this.nbVoisins+1; 
			}
			else {
				this.successeurs.add(v); 
				this.arcs.add(d); 
				this.nbVoisins = this.nbVoisins+1; 
				// n est pas voisin 
			}
			
		}else {
			this.successeurs = new ArrayList<Noeud>(); 
			this.successeurs.add(v); 
			this.arcs = new ArrayList<Integer>(); 
			this.arcs.add(d);
			this.nbVoisins = this.nbVoisins+1; 
		}
		
		// a completer
		return -1;
	}
	
	
	// si v n'est pas voisin, ne fait rien
	// sinon enleve v de la liste de voisins
	// renvoie le nouveau nombre de voisins
	public int enleveVoisin(Noeud v) {
		return -1; 
		
	}

	@Override
	public int compareTo(Noeud o) {
		// TODO Auto-generated method stub
		if(id > o.id) {
			return 1; 
		}else if(id < o.id) {
			return -1; 
		}else {
			return 0;
		}
	}
	
	public void afficheNoeud() {
		System.out.println("Id: "+this.id);
		System.out.println("Nombre de voisin: "+this.nbVoisins);
		System.out.println("Voisin :");
		if(this.successeurs != null) {
			for(int i = 0 ; i < this.successeurs.size(); i++) {
				System.out.print(this.successeurs.get(i).id);
			}
			System.out.println("");
			System.out.println(" ");
			System.out.println("Arcs :");
			for(int i = 0 ; i < this.arcs.size(); i++) {
				System.out.print(this.arcs.get(i)+" ");
			}
			System.out.println("");
		}else {
			System.out.println("Vide");
		}
		
	}
	
	public static void main(String[] args) {
		Noeud N1 = new Noeud()	;
		N1.afficheNoeud();
		System.out.println("-------------------------------------");
		Noeud N2  = new Noeud(); 
		N2.afficheNoeud();
		System.out.println("-------------------------------------");
		N1.ajouteVoisin(N2, 1); 
		N1.ajouteVoisin(N2, 2); 
		N1.ajouteVoisin(N2, 1); 
		N1.afficheNoeud();
	}
	
}
