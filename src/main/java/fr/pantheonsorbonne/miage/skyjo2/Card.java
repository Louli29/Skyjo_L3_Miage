package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Card {

    private Valeur valeur;
    public Object getValeur;

    public Card(Valeur valeur){
        this.valeur = valeur;
    }

    public int getValeur(){
        return this.valeur.getValeur();
    }

    public String toString(){
        return valeur.toString();
    }

    public static String cardsToString(Card[] cards) {
        return Arrays.stream(cards).map(Card::toString).collect(Collectors.joining(";"));
    }

}
