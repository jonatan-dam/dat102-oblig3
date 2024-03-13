package no.hvl.dat102.match;

import java.util.HashSet;
import java.util.Set;

public class Person {

	private String navn;
    private Set<String> hobbyer;

	
	public Person(String navn, String...hobbyer) {
		this.navn = navn;
		this.hobbyer = new HashSet<>();
		for(String hobby : hobbyer) {
			this.hobbyer.add(hobby);
		}
		
	}


	public String getNavn() {
		return navn;
	}


	public void setNavn(String navn) {
		this.navn = navn;
	}


	public Set<String> getHobbyer() {
		return hobbyer;
	}


	public void setHobbyer(Set<String> hobbyer) {
		this.hobbyer = hobbyer;
	}
	
	
}
