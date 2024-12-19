package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Random;

public class DumbPlayer extends Player {

    Random rd = new Random();

    public DumbPlayer( String name){
        super(name);
    }

    public void chooseKeepOrNot(Card card,boolean isFromTrash){
        System.out.println(" ");
        System.out.println(card.toString());   
        System.out.println(" "); 
        if(card.getValeur()<6){
            chooseWhereToReplace(card);
        }
        else{
            if(isFromTrash){
                chooseKeepOrNot(deck,false);
            }
            else{
                trash=card;
                revealCard();
            }
        }
    }

    public void chooseWhereToReplace(Card card){
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
            Card[] column =knownHand.get(i);
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
        chooseKeepOrNot(trash, true);
        knownHand.showHand();
        if(whereIsColumn() != -1){
            deleteColumn(whereIsColumn());
        }
    }


}
