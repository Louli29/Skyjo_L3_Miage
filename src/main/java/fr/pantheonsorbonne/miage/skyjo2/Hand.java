package fr.pantheonsorbonne.miage.skyjo2;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<SkyjoCard[]> hand=new ArrayList<>();

    public Hand(Deck d){
        this.hand=makeHand(d);
    }

    private List<SkyjoCard[]> makeHand(Deck d){
        List<SkyjoCard[]> hand = new ArrayList<>();
        for (int i=0; i<4;i++){
            SkyjoCard[] column=new SkyjoCard[3];
            for(int j=0; j<3; j++){
                column[j]=d.piocher();
            }
        hand.add(i,column);
        }
        return hand; 
    }

    public SkyjoCard getCard(int i, int j){ 
        return hand.get(i)[j]; 
    }

    public void deleteColumn(int column){
        hand.remove(column);
    }

    public SkyjoCard remplacerCarte(int i, int j, SkyjoCard newCard){
        SkyjoCard cardToDelete =this.hand.get(i)[j];
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




}
