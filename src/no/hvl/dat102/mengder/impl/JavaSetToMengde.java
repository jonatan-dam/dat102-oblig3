package no.hvl.dat102.mengder.impl;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import no.hvl.dat102.mengdeADT.MengdeADT;

public class JavaSetToMengde<T> implements MengdeADT<T> {

	private Set<T> mengde;
	private boolean initialisert = false;
	private int antall;
	
	public JavaSetToMengde() {
		mengde = new HashSet<>();
		initialisert = true;
		antall = 0;
	} //end kontrukstør
	
	
	@Override
	public boolean erTom() {
		sjekkInitialisering();
		return mengde.isEmpty();
	} //end erTom
	

	

	@Override
	public boolean inneholder(T element) {
		sjekkInitialisering();
		return mengde.contains(element);
	} //end inneholder
	

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		JavaSetToMengde<T> javaSetMengde = (JavaSetToMengde<T>) annenMengde;
	    return javaSetMengde.mengde.containsAll(mengde);
	
	} //end erDelmengdeAv
	

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		
		
		if(antall != annenMengde.antallElementer()){
			return false;
		} //end if
		
		for(T element : mengde) {
			if(!annenMengde.inneholder(element)) {
				return false;
			}
		} //end for-løkke
		
		for(T element : annenMengde.tilTabell()) {
			if(!inneholder(element)) {
				return false;
			}
		} //end for-løkke
		
		
		return true;
	} //end erLik
	

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		for(T element : mengde) {
			if(annenMengde.inneholder(element)) {
				return false;
			} //end if
		} //end for-løkke


		return true;
	} //end erDisjunkt
	

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		MengdeADT<T> snittMengde = new JavaSetToMengde<>();
		
		for(T element : mengde) {
			if(annenMengde.inneholder(element)) {
				snittMengde.leggTil(element);
			} //end if
		} //end for-løkke
		
		return snittMengde;
	} //end snitt
	

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		MengdeADT<T> unionMengde = new JavaSetToMengde<>();
		unionMengde.leggTilAlleFra(annenMengde);
		
		for(T element : mengde) {
			unionMengde.leggTil(element);
		} //end for-løkke
		
		
		return unionMengde;
	} //end union
	

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		MengdeADT<T> differanseMengde = new JavaSetToMengde<>();
		
		for(T element : mengde) {
			if(!annenMengde.inneholder(element)){
				differanseMengde.leggTil(element);
			} //end if
		} //end for-løkke
		
		return differanseMengde;
	} //end minus
	

	@Override
	public void leggTil(T element) {
		sjekkInitialisering();
		mengde.add(element);
		antall++;
	} //end leggTil
	

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		T[] tempTab = annenMengde.tilTabell();
		
		for(T element : tempTab) {
			if(!inneholder(element)) {
				
				leggTil(element);
				
			} //end if
		} //end for-løkke
		
	} //end leggTilAlleFra
	

	@Override
	public T fjern(T element) {
		sjekkInitialisering();
		
		T temp = element;
		
		boolean fjernet = mengde.remove(element);
		
		if(fjernet == false) {
			temp = null;
		} //end if
		
		return temp;
		
	} //end fjern
	

	
	@SuppressWarnings("unchecked")
	@Override
	public T[] tilTabell() {
		sjekkInitialisering();
		return (T[]) mengde.toArray();
	} //end tilTabell
	

	@Override
	public int antallElementer() {
		sjekkInitialisering();
		return mengde.size();
	} //end antallElementer
	
	/** Kaster et SecurityException om objektet ikke er intialisert skikkelig */
	private void sjekkInitialisering() {
		if(!initialisert) {
			throw new SecurityException("Mengden er ikke initialisert skikkelig");
		}
	} //end sjekkInitialisering
	
	@Override
	public int hashCode() {
		return Objects.hash(initialisert, mengde);
	} //end hashCode


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JavaSetToMengde<?> other = (JavaSetToMengde<?>) obj;
		return initialisert == other.initialisert && Objects.equals(mengde, other.mengde);
	} //end equals


}
