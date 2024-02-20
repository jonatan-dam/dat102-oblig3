package no.hvl.dat102.mengder.impl;

import java.util.Arrays;
import no.hvl.dat102.mengdeADT.MengdeADT;


public class TabellMengde<T> implements MengdeADT<T> {

	private T[] mengde;
	private int antall;
	private static final int DEFAULT_KAPASITET = 25;
	private boolean initialisert = false;
	private static final int MAKS_KAPASITET = 10000;
	
	

	


	

	/** Lager en tom mengde med 25 i kapasitet */
	public TabellMengde() {
		this(DEFAULT_KAPASITET);
	} // end konstruktør
	
	
	/** Lager en tom mengde med ønsket kapasitet
	 * @param capacity Heltallet ønsket kapasitet. */
	@SuppressWarnings("unchecked")
	public TabellMengde(int kapasitet) {
		sjekkKapasitet(kapasitet); // Lar ikke mengden opprettes dersom ønsket kapasitet er større enn tillat maks kapasitet
		
		T[] tempMengde = (T[]) new Object[kapasitet];
		mengde = tempMengde;
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
		for(int i = 0; i < antall; i++) {
			if(mengde[i] != null && mengde[i].equals(element)) {
				return true;
			}
		}
		
		return false;
	} //end inneholder
	

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		sjekkInitialisering();
		
		for(T elementer : mengde) {
			if(elementer != null && !annenMengde.inneholder(elementer)) {
				return false; // Dersom annenMengde ikke inneholder et element fra vår opprinnelige mengde,
				              // er vår opprinnelige mengde ikke en delmengde av annenMengde
			}
		}
		
		return true;
	} //end erDelMengdeAv
	

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		
		return false;
		
	} //end erLik
	

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void leggTil(T element) {
		sjekkInitialisering(); //Lar ikke metoden kjøre dersom mengden ikke er initialisert skikkelig
		if(antall < mengde.length) { // Sjekker at det er plass
			mengde[antall] = element;
			antall++;
		}else { // Dersom ikke plass, så utvides mengden før elementet legges til
			mengde = utvid(mengde);
			mengde[antall] = element;
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
	} //end leggTilAlleFRa
	

	@Override
	public T fjern(T element) {
		sjekkInitialisering();
		
		T tempElement = null;
		for(int i = 0; i < antall; i++) {
			if(mengde[i].equals(element)) {
				tempElement = mengde[i];
				mengde[i] = null;
				return tempElement;
			} //end if
		} //end løkke
		
		return tempElement;
	} //end fjern
	

	@Override
	public T[] tilTabell() {
		sjekkInitialisering();
		
		@SuppressWarnings("unchecked")
		T[] tabell = (T[]) new Object[antall];
		
		for(int i = 0; i < antall; i++) {
			tabell[i] = mengde[i];
		}
		
		return tabell;
	} //end tilTabell
	

	@Override
	public int antallElementer() {
		sjekkInitialisering();
		return antall;
	} //end antallElementer
	
	
	
	private T[] utvid(T[] fullTab) {
		
		sjekkInitialisering(); // Lar ikke metoden kjøre på mengde som ikke er opprettet på skikkelig måte
		int nyLengde = fullTab.length*2;
		sjekkKapasitet(nyLengde); // Lar ikke metoden kjøre på mengden dersom ønsket ny lengde er større enn tillatt makslengde
		fullTab = Arrays.copyOf(fullTab, nyLengde);
		
		return fullTab;
	} //end utvid
	
	
	
	/** Kaster et SecurityException om objektet ikke er intialisert skikkelig */
	private void sjekkInitialisering() {
		if(!initialisert) {
			throw new SecurityException("Mengden er ikke initialisert skikkelig");
		}
	} //end sjekkInitialisering
	
	
	
	/** Sjekker om ønsket kapasitet er større enn tillatt maks kapasitet
	 * @param kapasitet */
	private void sjekkKapasitet(int kapasitet) {
		if(kapasitet > MAKS_KAPASITET) {
			throw new IllegalStateException("Forsøkte å opprette en mengde som er større enn tillatt maks kapasitet");
		}
	} //end sjekkKapasitet
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(mengde);
		return result;
	} //end hashCode


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabellMengde other = (TabellMengde) obj;
		return Arrays.deepEquals(mengde, other.mengde);
	} //end equals


}
