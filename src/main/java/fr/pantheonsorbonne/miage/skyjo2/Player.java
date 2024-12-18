package fr.pantheonsorbonne.miage.skyjo2;
import java.util.Deque;
import java.util.Random;


public abstract class Player { 
    private final String name;
    private int score;
    Hand hand;
    KnownHand knownHand;
    Random rd = new Random();
    Deck d;
    Deque<Card>  poubelle;


    public Player(Deck d,Deque<Card>  p, String name, Hand hand, KnownHand knownHand){
        score=0;
        this.name=name;
        this.d=d;
        this.poubelle=p;
        this.hand=hand;
        this.knownHand=knownHand;
    }

    public void replaceCard(int numColumn, int index, Card carteRemplacante){
        this.knownHand.get(numColumn)[index]=carteRemplacante;
        poubelle.push(this.hand.remplacerCarte(numColumn,index,carteRemplacante));
        
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public void addToScore(int nbPoint){
        this.score+=nbPoint;
    }

    public void deleteColumn(Card card, int numColumn){
        Card[] column=knownHand.get(numColumn);
        for (int i=0; i<column.length; i++){
            poubelle.push(column[i]);
        }
        knownHand.remove(numColumn);
        hand.deleteColumn(numColumn);
        poubelle.push(card);
    }

    public void deleteColumn(int numColumn){
        Card[] column=knownHand.get(numColumn);
        for (int i=0; i<column.length; i++){
            poubelle.push(column[i]);
        }
        knownHand.remove(numColumn);
        hand.deleteColumn(numColumn);
    }

    public int countKnownPoint(){
        int nb=0;
        for (int i=0; i<knownHand.size();i++){
            for (int j=0; j<knownHand.get(i).length;j++){
                if(knownHand.get(i)[j]==null){
                    continue;
                }
                nb+=knownHand.get(i)[j].getValeur();
            }
        }
        return nb;
    }

    
    public abstract void chooseKeepOrNot(Card card, boolean isFromTrash);
    public abstract void chooseWhereToReplace(Card card);
    public abstract void revealCard();

    public abstract void jouer();

    

    
}
