package no.hvl.dat102.mengder.impl;


import no.hvl.dat102.mengdeADT.MengdeADT;

public class LenketMengde<T> implements MengdeADT<T> {

	private Node forsteNode;
	private int antall;
	private boolean initialisert = false;
	
	
	public LenketMengde() {
		forsteNode = null;
		antall = 0;
		initialisert = true;
	} //end konstruktør
	

	
	@Override
	public boolean erTom() {
		sjekkInitialisering();
		
		return antall == 0;
	} //end erTom
	

	@Override
	public boolean inneholder(T element) {
		sjekkInitialisering();
		
		Node currentNode = forsteNode;
		
		while (currentNode != null) {
			if(element.equals(currentNode.data)) {
				return true;
			}
			currentNode = currentNode.neste;
		}
		
		return false;
		//return (finnReferanse(element) != null);
	} //end inneholder
	

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		// TODO Auto-generated method stub
		return false;
	} //end erDelmengdeAv
	

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		// TODO Auto-generated method stub
		return false;
	} //end erLik
	
	

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		// TODO Auto-generated method stub
		return false;
	} //end erDisjunkt
	
	

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		MengdeADT<T> snittMengde = new LenketMengde<>();
		
		
		return snittMengde;
	} //end snitt
	
	

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		// TODO Auto-generated method stub
		return null;
	} //end union
	
	

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		MengdeADT<T> differanseMengde = new LenketMengde<>();
		int index = 0;
		Node currentNode = forsteNode;
				
		while(index < antall) {
			
			if(!annenMengde.inneholder(currentNode.data)) {
				differanseMengde.leggTil(currentNode.data);
				
			} //end if
			
			currentNode = currentNode.neste;
			index++;
			
		} //end while
		
		return differanseMengde;
	} //end minus
	
	

	@Override
	public void leggTil(T element) {
		sjekkInitialisering();
		
		if(!inneholder(element)) {
			Node nyNode = new Node(element);
			nyNode.neste = forsteNode; // Sier at den nye noden skal referere til resten av lenken
			
			forsteNode = nyNode; // Setter den nye noden til å være starten på lenken
			antall++;
		}
		
	} //end leggTil
	
	

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		for(T element : annenMengde.tilTabell()) {
			if(!inneholder(element)) {
				
				leggTil(element);
				
			}
		}
		
	} //end leggTilAlleFra
	
	

	@Override
	public T fjern(T element) {
		sjekkInitialisering();
		
		Node forrige = null;
		Node currentNode = finnReferanse(element);
		
		
		if(currentNode == null) {
			return null;
		}
		
		if(currentNode == forsteNode) {
			forsteNode = currentNode.neste;
		}else {
			forrige = forsteNode;
			while (forrige.neste != currentNode) {
				forrige = forrige.neste;
			}
			forrige.neste = currentNode.neste;
		}
		
		antall--;
		
		return currentNode.data;
	} //end fjern
	
	

	@Override
	public T[] tilTabell() {
		sjekkInitialisering();
		
		@SuppressWarnings("unchecked")
		T[] tabell = (T[]) new Object[antall];
		
		int index = 0;
		Node currentNode = forsteNode;
		
		while((index < antall) && (currentNode != null)) { 
			tabell[index] = currentNode.data;
			index++;
			currentNode = currentNode.neste;
		} //end while
	
		
		return tabell;
	} //end tilTabell
	
	

	@Override
	public int antallElementer() {
		sjekkInitialisering();
		return antall;
	} //end antallElementer

	
	/** Kaster et SecurityException om objektet ikke er intialisert skikkelig */
	private void sjekkInitialisering() {
		if(!initialisert) {
			throw new SecurityException("Mengden er ikke initialisert skikkelig");
		}
	} //end sjekkInitialisering
	
	
	/** Finner et gitt element i mengden
	 * @param element Elementet som skal bli lett etter
	 * @return En referanse til noden som inneholder elementet, hvis funnet. Null hvis ikke */
	private Node finnReferanse(T element) {
		
		Node currentNode = forsteNode;
		int teller = 0;
		
		while((teller < antall) && currentNode != null) {
			if(element.equals(currentNode.data)) {
				return currentNode;
			}else {
				currentNode = currentNode.neste;
			}
		} //end while
		
		return currentNode;
	} //end finnReferanse
	
	
	
	
	private class Node {
		
		private T data; //Element i mengde
		private Node neste; // Adresse til neste node
		
		private Node(T dataDel) {
			this(dataDel, null);
		} //end konstruktør
		
		private Node(T dataDel, Node nesteNode) {
			data = dataDel;
			neste = nesteNode;
		} //end konstruktør
		
		
		
		
	} //end Node
	
	
	
}
