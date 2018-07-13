package vn.edu.ifi.tp_ro.graphe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import vn.edu.ifi.tp_ro.fichier.Fichier;
import vn.edu.ifi.tp_ro.noeud.Noeud;
import vn.edu.ifi.tp_ro.utilisation.Utilisation;

public class Graphe {

	protected int nbNoeuds;
	protected ArrayList<Noeud> noeuds;
	
	public Graphe() {
		nbNoeuds = 0;
		noeuds = new ArrayList<Noeud>();
	}
	
	public Graphe(Noeud[] nds) {
		nbNoeuds = nds.length;
		noeuds = new ArrayList<Noeud>(nbNoeuds);
		for (int i = 0; i < nbNoeuds; i++) {
			noeuds.add(i, nds[i]);
		}
	}
	
	public int getNbNoeuds() {
		return nbNoeuds;
	}

	public void setNbNoeuds(int nbNoeuds) {
		this.nbNoeuds = nbNoeuds;
	}

	public ArrayList<Noeud> getNoeuds() {
		return noeuds;
	}
	
	public void setNoeuds(Noeud noeuds) {
		this.setNbNoeuds(this.getNbNoeuds()+1);
		this.noeuds.add(noeuds);
	}
	
	public void setNoeuds(ArrayList<Noeud> noeuds) {
		if(this.getNoeuds().size() == 0) {
			this.noeuds = noeuds;
		}else {
			for(int i= 0 ; i< noeuds.size(); i++) {
				this.setNoeuds(noeuds.get(i));
			}
		}
		
	}
	

	public static ArrayList<Noeud> parcoursGraphe(Graphe g) {
		System.out.println("nombre de noeud : "+g.getNbNoeuds());
		LinkedList<Noeud> Q = new LinkedList<Noeud>(); 
	
		ArrayList<Noeud> S = new ArrayList<Noeud>(); 
		if(g.getNbNoeuds() > 0) {
			Q.add(g.getNoeuds().get(0));   
			while(!Q.isEmpty()){
				Noeud v = Q.pop();  
				System.out.println(" taille pop "+ Q.size());
				System.out.print("Noeude ID : "+v.getId()+" voisons :");
				for(int i = 0; i < v.getNbVoisins(); i++) {
					System.out.print(v.getSuccesseurs().get(i).getId()+"**");
					if(!Q.contains(v.getSuccesseurs().get(i)) && !S.contains(v.getSuccesseurs().get(i))) {
						Q.add(v.getSuccesseurs().get(i));
						System.out.print(" element ajoute "+v.getSuccesseurs().get(i).getId());
						System.out.print(" entre "+ i);
						System.out.print(" taille "+ Q.size());
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
	
	public  Noeud rechercheGraphe(int id) {
		// la FIFO des noeuds a parcourir 
		LinkedList<Noeud> Q = new LinkedList<Noeud>(); 
		// liste de noeuds deja parcourus 
		ArrayList<Noeud> S = new ArrayList<Noeud>();
		Noeud  trouver = null; 
		int indexNoeud = 0 ; 
		if(this.getNbNoeuds() > 0) {
			Q.add(this.getNoeuds().get(0));   
			while(!Q.isEmpty() && trouver == null){
				Noeud v = Q.pop();
				if(v.getId() != id) {
					for(int i = 0; i < v.getNbVoisins(); i++) {
						if(!Q.contains(v.getSuccesseurs().get(i)) && !S.contains(v.getSuccesseurs().get(i))) {
							Q.add(v.getSuccesseurs().get(i));
						}
					}
					S.add(v); 
				}else {
					trouver = v; 
				}
			}
			
		}
		return trouver ; 
	}
	
	
	/**
	*retourne 1 si noeud existe
	*retourne -1 si noeud n existe pas 
	*/
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
	
	/**
	 * inverser tous les arcs sur ce chemin
	 * @param chemin
	 * @return
	 */
	public boolean inverseChemin(LinkedList<Noeud> chemin) {
		return true ;
	}
	
	public static Graphe creeGraphe(String fichier) {
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
			System.out.println(" mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
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
	
	
	
	/**
	 * cree le reseau a flot correspondant a partir du fichier passe en entree
	 * @param fichier
	 * @return
	 */
	public static Graphe creeReseau(String fichier) {
		System.out.println(" ");
		Graphe graphe;
		if(!fichier.isEmpty()) {
			graphe = new Graphe();
			int [][] matrice = null ;
			Fichier taskfile = new Fichier(fichier); 
			matrice = taskfile.readFichier();
			 int nbnoeud = matrice.length - 1;
			
			 while(nbnoeud >=0){
				System.out.print(nbnoeud+"------");
				Noeud newNoeud = new Noeud(nbnoeud);
				for(int j = 0; j< matrice[nbnoeud].length;j++) {
					if(matrice[nbnoeud][j] != 0) {
						System.out.print(matrice[nbnoeud][j]);
						newNoeud.setSuccesseurs(new Noeud(matrice[nbnoeud][j]), 1);
					}
				}
				System.out.println(" ");
				graphe.setNoeuds(newNoeud);
				nbnoeud = nbnoeud -1;
			 }
		}else {
			graphe = null;
		}
		
		return graphe ;
	}
	
	/**
	 * algorithme Ford – Fulkerson utilisant le BFS avec la source s et le puit t.
	 * @param s
	 * @param t
	 * @return
	 */
	public int ff(Noeud s, Noeud t) {
		return 0;
	}
	
	public static void main(String[] args) {
		Graphe.parcoursGraphe(Utilisation.graphBi);
		
		
	}

	
}
