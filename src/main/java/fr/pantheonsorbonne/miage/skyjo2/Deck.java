package fr.pantheonsorbonne.miage.skyjo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {

    private List<Card> pioche;
    private final int nbNegTwo = 5;
    private final int nbZero = 15;
    private final int nbCardClassique = 10;
    Deque<Card> trash;
    
    
    public Deck(Deque<Card> poubelle){
        this.pioche=makePioche();
        this.trash=poubelle;
        Collections.shuffle(pioche);
    }

    public List<Card> makePioche(){
        List<Card> pioche = new ArrayList<>();
        for (Valeur val : Valeur.values()){
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
        if (pioche.isEmpty()) {
            reMakeDeck();
        }
        Card card=pioche.get(0);
        pioche.remove(0);
        return  card ; 
    }

    private void reMakeDeck() {
        pioche.addAll(trash);
        trash.clear();
        Collections.shuffle(pioche);
        trash.push(piocher());
    }

}
