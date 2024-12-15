package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Random;

public class DumbPlayer extends Player {

    Random rd = new Random();

    public DumbPlayer(Deck d,Poubelle poubelle){
        super(d, poubelle);
    }

    public void chooseKeepOrNot(SkyjoCard card,boolean isFromTrash){
        System.out.println(" ");
        System.out.println(card.toString());   
        System.out.println(" "); 
        if(card.getValeur()<5){
            chooseWhereToReplace(card);
        }
        else{
            if(isFromTrash){
                chooseKeepOrNot(d.piocher(),false);
            }
            else{
                poubelle.addCard(card);
                revealCard();
            }
        }
    }

    public void chooseWhereToReplace(SkyjoCard card){
        int i =rd.nextInt(0,knownHand.size());
        int j=rd.nextInt(0,knownHand.get(0).length);
        replaceCard(i, j, card);
    }

    public void revealCard(){
        int i;
        int j;
        do {
            i =rd.nextInt(0,knownHand.size());
            j=rd.nextInt(0,knownHand.get(0).length);
        }while(knownHand.get(i)[j] != null);
        knownHand.get(i)[j]=hand.getCard(i, j);//mettre les memes méthodes pour hand et knownhand et faire de l'héritage
    }

    public int whereIsColumn(){
        for (int i=0; i<knownHand.size();i++){
            SkyjoCard[] column =knownHand.get(i);
            if(knownHand.getValeur(column[0])==knownHand.getValeur(column[1]) && knownHand.getValeur(column[1])==knownHand.getValeur(column[2])){
                if(knownHand.getValeur(column[0])==-3){
                    return -1;
                }
                return i;
            }
        }
        return -1;
    }

    public void jouer(){
        knownHand.showHand();
        chooseKeepOrNot(poubelle.getLastCard(), true);
        knownHand.showHand();
        if(whereIsColumn() != -1){
            deleteColumn(whereIsColumn());
        }
    }


}
