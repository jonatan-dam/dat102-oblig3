package no.hvl.dat102.match;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HobbyMatchMainTest {
	
	private Person arne;
	private Person lise;
	private Person petter;
	private Person karl;
	private Person ole;
	
	@BeforeEach
	void nullstill() {
		arne = new Person("Arne", "jakt", "sykling", "venner", "data");
		lise = new Person("Lise", "jakt", "venner", "fisking", "golf");
		petter = new Person("Petter", "jakt", "tur", "fotball");	
		ole = new Person("Ole", "sykling", "data");
        karl = new Person("Karl", "fotball", "tur");
	}
	
	 @Test
	    void selvMatchSkalGiMaksScore() {
	        double forventetScore = 4.0;
	        double faktiskScore = HobbyMatchMain.match(arne, arne);
	        assertEquals(forventetScore, faktiskScore);
	    }

	    @Test
	    void partnerMatchSkalGiRiktigScore() {
	        // Antall felleshobbyer: 2 (jakt, venner)
	        // Arnes egne hobbyer: 2 (sykling, data)
	        // Lises egne hobbyer: 2 (fisking, golf)
	        // Totale hobbyer: 6 (2 felles + 2 Arne alene + 2 Lise alene)
	        // Forventet score: (2 - (2 + 2) / 6) = -0.666666...
	       double forventetScore = (2-(2+2)/6); 
	       double faktiskScore = HobbyMatchMain.match(arne, lise);
	       assertEquals(forventetScore, faktiskScore);

	    }

	    @Test
	    void ingenFellesHobbyerSkalGiMinimum() {
	        // Antall felleshobbyer: 0
	        // Arnes egne hobbyer: 2 (sykling, data)
	        // Petters egne hobbyer: 2 (fotball, tur)
	        // Totale hobbyer: 4 (0 felles + 2 Arne alene + 2 Petter alene)
	        // Forventet score: (0 - (2 + 2) / 4) = -1.0

	    	double forventetScore = (0-(2+2)/4);
	        double faktiskScore = HobbyMatchMain.match(ole, karl);
	        assertEquals(forventetScore, faktiskScore);
	    }
	    
	    @Test
	    void inversMatchSkalGiLikScore() {
	        double matchScore = HobbyMatchMain.match(ole, karl);
	        double inversMatchScore = HobbyMatchMain.match(ole, karl);
	        assertEquals(matchScore, inversMatchScore);
	    }
	}
