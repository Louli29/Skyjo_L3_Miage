package fr.pantheonsorbonne.miage.skyjo2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class KnownHand {
    List<Card[]> knownHand=new ArrayList<>();
    Hand hand;

    
    public KnownHand(Hand hand, Deque<Card>  poubelle){
        this.hand=hand;
        this.knownHand=initialiserMainConnu(hand);
        
    }

    private List<Card[]> initialiserMainConnu(Hand hand) {
        List<Card[]> knownHand = new ArrayList<>();
        for (int i=0; i<4;i++){
            Card[] column=new Card[3];
            knownHand.add(i,column);
        }
        
        knownHand.get(0)[0]=this.hand.getCard(0, 0);
        knownHand.get(1)[1]=this.hand.getCard(1, 1);
        
        
        return knownHand; 
    }

    public int nbKnownCard(){
        int nb=0;
        for (Card[] column : knownHand){
            nb+=nbKnownCard(column);
        }
        return 12- nb;
    }

    public int nbKnownCard(Card[] column){
        int nb=0;
        for (Card card : column){
            if (card == null){
                nb+=1;
            }
        }
        return nb;
    }

    public Card[] get(int numColumn) {
        return knownHand.get(numColumn);
    }

    public int size(){
        return knownHand.size();
    }

    public void remove(int numColumn){
        knownHand.remove(numColumn);
    }

    public void showHand(){
        for(int j=0; j<knownHand.get(0).length;j++){
            for (int i=0; i<knownHand.size();i++){
                System.out.print(knownHand.get(i)[j]+"           ");
            }
            System.out.println("");

        }
    }

    public int getIndexColumnSameCard(Card card){
        int maxNb=0;
        int numColumnMax=-1;
        for (int i =0; i<knownHand.size();i++){
            int nbCard = cardOccurenceColumn(card, knownHand.get(i));
            if (nbCard>maxNb){
                numColumnMax= i;
            }
        }
        return numColumnMax;
    }

    public int cardOccurenceColumn(Card card, Card[] column){ 
        int nbSameCard=0;
        for (int i=0;i<column.length;i++){
            if (column[i]==null){
                continue;
            }
            else if(column[i].getValeur()==card.getValeur()){
                nbSameCard+=1;
            }
        }
        return nbSameCard;
    }

    public int getHighCard(Card[] column){ //to do implémenter si carte null continue sinon comparer maintenant facile vu q'un co la carte 00
        return 0;
    }

    public int getValeur(Card card){//voire si vraiment utile
        if(card==null){
            return -3;
        }
        return card.getValeur();
    }

    
}
