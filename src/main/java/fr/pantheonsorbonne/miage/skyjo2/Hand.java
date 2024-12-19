package fr.pantheonsorbonne.miage.skyjo2;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card[]> hand=new ArrayList<>();

    public Hand(Card[] listCard){
        this.hand=makeHand(listCard);
    }

    private List<Card[]> makeHand(Card[] listCard){
        List<Card[]> hand = new ArrayList<>();
        int index=0;
        for (int i=0; i<4;i++){
            Card[] column=new Card[3];
            for(int j=0; j<3; j++){
                column[j]=listCard[index];
                index++;
            }
        hand.add(i,column);
        }
        return hand; 
    }

    public Card getCard(int i, int j){ 
        return hand.get(i)[j]; 
    }

    public void deleteColumn(int column){
        hand.remove(column);
    }

    public Card remplacerCarte(int i, int j, Card newCard){
        Card cardToDelete =this.hand.get(i)[j];
        this.hand.get(i)[j]=newCard;
        return cardToDelete;
    }

    public void showHand(){
        for(int j=0; j<hand.get(0).length;j++){
            for (int i=0; i<hand.size();i++){
                System.out.print(hand.get(i)[j]+"           ");
            }
            System.out.println("");

        }
        System.out.println("\n");
    }

    public int getNbPoint(){
        int points=0;
        for(int j=0; j<hand.get(0).length;j++){
            for (int i=0; i<hand.size();i++){
                points += hand.get(i)[j].getValeur();
            }
        }
        return points;
    }




}
