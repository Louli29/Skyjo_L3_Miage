package fr.pantheonsorbonne.miage.skyjo2;
import java.util.Deque;
import java.util.Random;


public abstract class Player { 
    protected final String name;
    protected int score;
    protected Hand hand;
    public KnownHand knownHand;
    Random rd = new Random();
    Card deck;
    Card trash;


    public Player(String name){
        score=0;
        this.name=name;
    }

    public void setPlayerRound(Hand hand ){
        this.hand=hand;
        this.knownHand=new KnownHand(this.hand);
    }

    public void setPlayerTour(Card [] deckAndTrash){
        this.deck=deckAndTrash[0];
        this.trash=deckAndTrash[1];
    }

    public KnownHand getknownHand(){
        return knownHand;
    }

    public Card getTrash(){
        return trash;
    }

    public void replaceCard(int numColumn, int index, Card carteRemplacante){
        Card cardToReplace=knownHand.get(numColumn)[index];
        if (carteRemplacante.getValeur()==knownHand.getValeur(cardToReplace)){
            revealCard();
        }
        else{
            this.knownHand.get(numColumn)[index]=carteRemplacante;
            trash=this.hand.remplacerCarte(numColumn,index,carteRemplacante);
        }

        
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
            trash=column[i];
        }
        knownHand.remove(numColumn);
        hand.deleteColumn(numColumn);
        trash=card;
    }

    public void deleteColumn(int numColumn){
        Card[] column=knownHand.get(numColumn);
        for (int i=0; i<column.length; i++){
            trash=column[i];
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

    public Hand getHand() {
        return hand;
    }

    public Card getDeck() {
        return deck;
    }

    
    public abstract void chooseKeepOrNot(Card card, boolean isFromTrash);
    public abstract void chooseWhereToReplace(Card card);
    public abstract void revealCard();

    public abstract void jouer();

    

    
}
