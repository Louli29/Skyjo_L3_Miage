package fr.pantheonsorbonne.miage.skyjo2;
import java.util.Random;


public abstract class Player {
    Hand hand;
    KnownHand knownHand;
    Random rd = new Random();
    Deck d;
    Poubelle poubelle;


    public Player(Deck d,Poubelle p){
        this.d=d;
        this.poubelle=p;
        this.hand=new Hand(d);
        this.knownHand=new KnownHand(hand,poubelle);
    }

    public void replaceCard(int numColumn, int index, SkyjoCard carteRemplacante){
        this.knownHand.get(numColumn)[index]=carteRemplacante;
        poubelle.addCard(this.hand.remplacerCarte(numColumn,index,carteRemplacante));
        
    }

    public void deleteColumn(SkyjoCard card, int numColumn){
        SkyjoCard[] column=knownHand.get(numColumn);
        for (int i=0; i<column.length; i++){
            poubelle.addCard(column[i]);
        }
        knownHand.remove(numColumn);
        hand.deleteColumn(numColumn);
        poubelle.addCard(card);
    }

    public void deleteColumn(int numColumn){
        SkyjoCard[] column=knownHand.get(numColumn);
        for (int i=0; i<column.length; i++){
            poubelle.addCard(column[i]);
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

    
    public abstract void chooseKeepOrNot(SkyjoCard card, boolean isFromTrash);
    public abstract void chooseWhereToReplace(SkyjoCard card);
    public abstract void revealCard();

    public abstract void jouer();

    

    
}
