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
				System.out.print("Noeude ID : "+v.getId()+" voisons :");
				for(int i = 0; i < v.getNbVoisins(); i++) {
					System.out.print(v.getSuccesseurs().get(i).getId()+"**");
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
	/**
	 * Dans la classe Graphe, écrire une méthode
	 * public LinkedList<Noeud> cheminBFS(Noeud s, Noeud t) qui cherche un plus court chemin (en nombre d’arc) du nœud s au nœud t. La
	 * méthode renvoi null si un tel chemin n’existe pas.
	 * 
	 * @param s noeud 
	 * @param t noeud 
	 * @return liste chainee de noeud entre s et t
	 */
	public LinkedList<Noeud> cheminBFS(Noeud s, Noeud t){
		System.out.println("\n source "+s.getId()+" et destination "+t.getId());
		LinkedList<Noeud> chemin = new LinkedList<Noeud>();
		ArrayList<Noeud> visite = new ArrayList<Noeud>();
		LinkedList<Noeud> candidate = new LinkedList<Noeud>();
		if(s.compareTo(t) == 0) {
			System.out.println(" egalite entre "+s.getId()+" et "+t.getId());
			chemin.push(s);
		}else {
			boolean see = false;
			candidate.add(s);
			while(!candidate.isEmpty() && see == false) {
				System.out.println(" taille candidate "+candidate.size());
				Noeud v = candidate.pop();
				if(v.compareTo(t) != 0) {
					for(int i = 0; i <v.getSuccesseurs().size();i++) {
						if(candidate.contains(v.getSuccesseurs().get(i)) == false && !visite.contains(v.getSuccesseurs().get(i))) {
							candidate.add(v.getSuccesseurs().get(i));
						}
					}
					visite.add(v);
				}else {
					see = true;
				}
			}
			if(see == false) {
				chemin = null ;
			}else {
				chemin.add(t);
				System.out.print("\n-----Le court chemain de "+s.getId()+" à "+t.getId()+" est :"+ t.getId()+" ");
				Noeud m = t;
				int k = 0;
				while(k < visite.size()) {
					Noeud v = visite.get(k);
					int j = 0;
					while(j < v.getSuccesseurs().size()  && v.getSuccesseurs().get(j).compareTo(m)!=0) {
						j=j+1;
					}
					if(j< v.getSuccesseurs().size()) {
						k=0;
						m = v ;
						System.out.print(v.getId()+" ");
						chemin.add(v);
					}else {
						k=k+1;
					}
				}
		
			}
		}
			
		return chemin;
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
		int index = 0;
		boolean noChemin = true;
		while(index < chemin.size()-1 && noChemin == true ) {
			if(this.cheminBFS(chemin.get(index), chemin.get(index+1))!= null) {
				chemin.get(index).getSuccesseurs().remove(chemin.get(index+1));
				chemin.get(index).setNbVoisins(chemin.get(index).getNbVoisins()-1); 
				chemin.get(index+1).getSuccesseurs().add(chemin.get(index));
				chemin.get(index+1).setNbVoisins(chemin.get(index+1).getNbVoisins()+1);
			}else {
				noChemin = false;
			}
			index = index+1;
		}
		return noChemin ;
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
