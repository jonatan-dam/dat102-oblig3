package no.hvl.dat102.soking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class SokSammenligning {

    public static void main(String[] args) {
        final int ANT_ELEMENT = 100000;
        final int ANT_SOEK = 10000;
        Random rand = new Random();
        
        // Oppretter HashSet og tabell
        HashSet<Integer> set = new HashSet<>();
        Integer[] tabell = new Integer[ANT_ELEMENT];
        
        // Genererer 100.000 ulike tall
        int tall = 376; // Startverdi
        for (int i = 0; i < ANT_ELEMENT; i++) {
            while (!set.add(tall)) { // Sjekker om tallet er lagt til før
                tall = (tall + 45713) % 1000000;
            }
            tabell[i] = tall;
            tall = (tall + 45713) % 1000000;
        }

        // Sortere tabellen for binærsøk
        Arrays.sort(tabell);

        // Generer 10.000 tilfeldige tall for søk
        Integer[] soekTall = new Integer[ANT_SOEK];
        for (int i = 0; i < ANT_SOEK; i++) {
            soekTall[i] = rand.nextInt(1000000);
        }
        
        // Søk i HashSet og mål tid
        long startTidHashSet = System.nanoTime();
        int funnetHashSet = 0;
        for (int tallSoek : soekTall) {
            if (set.contains(tallSoek)) {
                funnetHashSet++;
            }
        }
        long sluttTidHashSet = System.nanoTime();
        
        // Søk i tabell og mål tid
        long startTidTabell = System.nanoTime();
        int funnetTabell = 0;
        for (int tallSoek : soekTall) {
            if (Arrays.binarySearch(tabell, tallSoek) >= 0) {
                funnetTabell++;
            }
        }
        long sluttTidTabell = System.nanoTime();

        // Resultater
        long tidHashSet = sluttTidHashSet - startTidHashSet;
        long tidTabell = sluttTidTabell - startTidTabell;

        System.out.println("HashSet - Antall funnet: " + funnetHashSet + ", Tid (ns): " + tidHashSet);
        System.out.println("Tabell - Antall funnet: " + funnetTabell + ", Tid (ns): " + tidTabell);

      
    }
}
