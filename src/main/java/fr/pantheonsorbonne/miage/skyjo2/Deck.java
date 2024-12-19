package fr.pantheonsorbonne.miage.skyjo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {

    private List<Card> deck;
    private final int nbNegTwo = 5;
    private final int nbZero = 15;
    private final int nbCardClassique = 10;
    Deque<Card> trash;
    
    
    public Deck(Deque<Card> trash){
        this.deck=makePioche();
        this.trash=trash;
        Collections.shuffle(deck);
    }

    public List<Card> makePioche(){
        List<Card> pioche = new ArrayList<>();
        for (Value val : Value.values()){
            if (val.getValeur()==-2){
                for (int i=0; i<nbNegTwo; i++){
                    pioche.add(new Card(val));
                }
            }
            else if (val.getValeur()==0){
                for (int i=0; i<nbZero; i++){
                    pioche.add(new Card(val));
                }
            }
            else{
                for (int i=0; i<nbCardClassique; i++){
                    pioche.add(new Card(val));
                }
            }
        }
        return pioche;

    }

    public Card piocher(){
        if (deck.isEmpty()) {
            reMakeDeck();
        }
        Card card=deck.get(0);
        deck.remove(0);
        return  card ; 
    }

    private void reMakeDeck() {
        deck.addAll(trash);
        trash.clear();
        Collections.shuffle(deck);
        trash.push(piocher());
    }

    public void add(Card card) {
        deck.add(card);
    }

}
