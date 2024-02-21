package no.hvl.dat102.mengder.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.mengdeADT.MengdeADT;




public class TabellMengdeTest {
	
	private MengdeADT<Integer> mengde0;
	private MengdeADT<Integer> mengde1;
	private MengdeADT<Integer> mengde2;
	private MengdeADT<Integer> mengde3;
	private MengdeADT<Integer> mengde4;
	
	@BeforeEach
	void nullstill() {
		mengde0 = new TabellMengde<Integer>();
		
		mengde1 = new TabellMengde<Integer>();
		mengde1.leggTil(1);
		
		
		mengde2 = new TabellMengde<Integer>();
		mengde2.leggTil(3);
		mengde2.leggTil(1);
		
		mengde3 = new TabellMengde<Integer>();
		mengde3.leggTil(3);
		mengde3.leggTil(1);
		
		mengde4 = new TabellMengde<Integer>();
		mengde4.leggTil(3);
	}
	
	/** Ting som må testes for en TabellMengde:
	 * - En ny mengde skal være tom
	 * - En mengde med ett eller to elementer skal ikke være tom
	 * - leggTil skal legge til et element i mengden
	 * - erTom skal sjekke om mengden er tom
	 * - inneholder skal sjekke om mengden inneholder et gitt element
	 * - erDelMengdeAv skal sjekke om en mengde er en delmengde av en annen mengde
	 * - erLik skal sjekke om en mengde er lik en annen mengde
	 * - erDisjunkt skal finne om to mengder er disjunkte
	 * - snitt skal finne snittet av to mengder
	 * - union skal finne unionen av to mengder
	 * - minus skal finne differansen mellom to mengder
	 * - leggTilAlleFra skal legge alle elementer fra en annen mengde til i mengden
	 * - fjern skal fjerne og returnere et element fra mengden
	 * - tilTabell skal returnere en tabell av elementene i mengden, tabellen skal ha lik størrelse som antall elementer i mengden
	 * - antallElementer skal returnere antall elementer i mengden
	 * 
	 */
	
	@Test
	void nyMengdeSkalVaereTom() {
		assertTrue(mengde0.erTom());
	} //end nyMengdeSkalVaereTom
	
	
	@Test
	void mengdeMedElementerSkalIkkeVaereTom() {
		assertFalse(mengde1.erTom());
		assertFalse(mengde2.erTom());
		assertFalse(mengde3.erTom());
		assertFalse(mengde4.erTom());
	} //end mengdeMedElementerSkalIkkeVaereTom
	
	
	@Test
	void leggTilSkalLeggeTilEtElement() {
		mengde0.leggTil(1);
		assertFalse(mengde0.erTom());
		assertTrue(mengde0.inneholder(1));
		mengde0.leggTil(1);
		assertEquals(mengde0, mengde1);
	} //end leggTilSkalLeggeTilEtElement
	
	
	@Test
	void erTomSkalSjekkeOmMengdeErTom() {
		assertTrue(mengde0.erTom());
		assertFalse(mengde1.erTom());
		assertFalse(mengde2.erTom());
		assertFalse(mengde3.erTom());
		assertFalse(mengde4.erTom());
	} //end erTomSkalSjekkeOmMengdeErTom
	
	
	@Test
	void erDelMengdeAvSkalSjekkeOmMengdeErDelmendeAvAnnenMengde() {
		assertTrue(mengde1.erDelmengdeAv(mengde2));
		assertFalse(mengde2.erDelmengdeAv(mengde1));
		assertTrue(mengde4.erDelmengdeAv(mengde3));
		assertFalse(mengde3.erDelmengdeAv(mengde0));
	} //end erDelMengdeAvSkalSjekkeOmMengdeErDelmendeAvAnnenMengde
	
	
	@Test
	void erLikSkalSjekkeOmMengdeErLikAnnenMengde() {
		assertTrue(mengde2.erLik(mengde3));
		assertFalse(mengde1.erLik(mengde2));
		assertFalse(mengde0.erLik(mengde1));
	} //end erLikSkalSjekkeOmMengdeErLikAnnenMengde
	
	
	@Test
	void erDisjunktSkalSjekkeOmToMengderErDisjunkte() {
		assertFalse(mengde1.erDisjunkt(mengde2));
		assertFalse(mengde2.erDisjunkt(mengde4));
		assertFalse(mengde3.erDisjunkt(mengde2));
		assertTrue(mengde0.erDisjunkt(mengde1));
		assertTrue(mengde4.erDisjunkt(mengde1));
		
	} //end erDisjunktSkalSjekkeOmToMengderErDisjunkte
	
	
	@Test
	void snittSkalFinneSnittetAvToMengder() {
		assertEquals(mengde2.snitt(mengde1), mengde1);
		assertEquals(mengde3.snitt(mengde2), mengde2);
		assertEquals(mengde4.snitt(mengde1), mengde0);
	} //end snittSkalFinneSnittetAvToMengder
	
	
	@Test
	void unionSkalFinneUnionAvToMengder() {
		assertEquals(mengde1.union(mengde4), mengde2);
		assertEquals(mengde3.union(mengde0), mengde3);
		assertEquals(mengde2.union(mengde3), mengde2);
	} //end unionSkalFinneUnionAvToMengder
	
	
	@Test
	void minusSkalFinneDifferansenAvToMengder() {
		assertEquals(mengde2.minus(mengde1), mengde4);
		assertEquals(mengde2.minus(mengde0), mengde2);
		assertEquals(mengde3.minus(mengde4), mengde1);
		assertEquals(mengde2.minus(mengde3), mengde0);
	} //end minusSkalFinneDifferansenAvToMengder
	
	
	@Test
	void leggTilAlleSkalLeggeTilAlleElementerSomIkkeErDuplikatFraEnAnnenMengde() {
		// TO DO
	} //end leggTilAlleSkalLeggeTilAlleElementerSomIkkeErDuplikatFraEnAnnenMengde
	
	@Test
	void fjernSkalFjerneOgReturnereElementFraMengden() {
		assertEquals(mengde2.fjern(3), 3);
		assertFalse(mengde2.inneholder(3));
		assertEquals(mengde1.fjern(1), 1);
		assertTrue(mengde1.erTom());
	} //end fjernSkalFjerneOgReturnereElementFraMengden
	
	
	@Test
	<T> void tilTabellSkalReturnereEnTabellAvElementerMedLikTabellstørrelseSomAntall() {
		@SuppressWarnings("unchecked")
		T[] testTab = (T[]) new Object[]{3, 1};
		
		@SuppressWarnings("unchecked")
		T[] mengde2Tab = (T[]) mengde2.tilTabell();
		
		assertEquals(testTab[0], mengde2Tab[0]);
		assertEquals(testTab[1], mengde2Tab[1]);
		
		assertEquals(mengde2Tab.length, mengde2.antallElementer());
		assertEquals(testTab.length, mengde2Tab.length);
	} //end tilTabellSkalReturnereEnTabellAvElementerMedLikTabellstørrelseSomAntall
	
	
	@Test
	void antallElementerSkalReturnereAntallElementerIMengden() {
		assertEquals(mengde0.antallElementer(), 0);
		assertEquals(mengde1.antallElementer(), 1);
		assertEquals(mengde2.antallElementer(), 2);
	} //end antallElementerSkalReturnereAntallElementerIMengden
	

}
