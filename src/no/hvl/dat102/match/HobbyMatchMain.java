package no.hvl.dat102.match;


import java.util.Iterator;
import java.util.Set;

public class HobbyMatchMain {

	public static void main(String[] args) {
		Person arne = new Person("Arne", "jakt", "sykling", "venner", "data");
		
		Person lise = new Person("Lise", "jakt", "venner", "fisking", "golf");
		
		Person petter = new Person("Petter", "jakt", "tur", "fotball");
		
		System.out.println(match(arne, arne));
	}
	
	static double match(Person a, Person b) {
		Set<String> aHobbyer = a.getHobbyer();
		Set<String> bHobbyer = b.getHobbyer();
		
		
		int fellesHobbyer = 0;
		int aEgenHobby = 0;
		int bEgenHobby = 0;
		double matchScore = 0;
		
		Iterator<String> hobbyIterator = aHobbyer.iterator();
		
		while(hobbyIterator.hasNext()) {
			if(bHobbyer.contains(hobbyIterator.next())) {
				fellesHobbyer++;
			}else {
				aEgenHobby++;
			} //end if
		} // end while
		
		hobbyIterator = bHobbyer.iterator();
		
		while(hobbyIterator.hasNext()) {
			if(!aHobbyer.contains(hobbyIterator.next())) {
				bEgenHobby++;
			} //end if
		} // end while
		
		int totaleHobbyer = fellesHobbyer + aEgenHobby + bEgenHobby;
		matchScore = fellesHobbyer - (aEgenHobby + bEgenHobby) / totaleHobbyer;

		return matchScore;
		
	}

}
