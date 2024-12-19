package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Card {

    private Value valeur;
    public Object getValeur;

    public Card(Value valeur){
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

    public static Card toCard(String cardStr) {
        Value valeur = Value.valueOf(cardStr.toUpperCase());
        return new Card(valeur);
    }
    

    public static Card[] stringToCards(String cards) {
        return Arrays.stream(cards.split(";")).map(Card::toCard).toArray(Card[]::new);
    }

}
