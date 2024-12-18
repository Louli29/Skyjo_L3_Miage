package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Deque;

public class SmartPlayer extends Player{

    public SmartPlayer(Deck d, Deque<Card>  poubelle, String name,Hand hand, KnownHand knownHand){
        super(d, poubelle, name, hand, knownHand);
    }

    public void chooseKeepOrNot(Card card, boolean isFromTrash){
        System.out.println("\n"+card.toString()+"\n");   

        if (card.getValeur()<=0){
                chooseWhereToReplace(card);
        }

        else if (knownHand.getIndexColumnSameCard(card) != -1 ){
            int numColumn = knownHand.getIndexColumnSameCard(card);
            if(knownHand.cardOccurenceColumn(card,knownHand.get(numColumn) ) == 2){
                deleteColumn(card, numColumn);
            }
            else{
                tryMakeColumn(card,numColumn,isFromTrash);
            }
        }
        else if(card.getValeur()<5){
            chooseWhereToReplace(card);
        }
        else{
            poubelle.push(card);
            if(isFromTrash){
                chooseKeepOrNot(deck.piocher(),false);
            }
            else{
                revealCard();
            }
        }
    }

    public void columnFail(Card card){
        if (card.getValeur()<5){
            chooseWhereToReplace(card);
        }
        else{
            poubelle.push(card);
            chooseKeepOrNot(deck.piocher(),false);
        }
    }

    public void makeColumn(Card card ,int numColumn){ 
        System.out.println("make column");
        Card[] column=knownHand.get(numColumn);
        int[] indexOthersCards=getIndexOtherCard(card,column);
        int bestIndex=chooseBestIndex(indexOthersCards,column);
        if(bestIndex==-1 && knownHand.getValeur(column[indexOthersCards[0]])<card.getValeur()){
            columnFail(card);
        }
        else{
            if(bestIndex==-1){
                bestIndex=indexOthersCards[0];
            }
            replaceCard(numColumn, bestIndex, card);
        }
    }

    public void tryMakeColumn(Card card, int numColumn, boolean isFromTrash){
        if ( knownHand.nbKnownCard()>7 && card.getValeur()>5 ){
            if(isFromTrash){
                columnFail(card);
            }
            else{
                poubelle.push(card);
                revealCard();
            }
            
        }
        else if (card.getValeur()>9 ){
            if(isFromTrash){
                columnFail(card);
            }
            else{
                poubelle.push(card);
                revealCard();
            }
        }
        else{
            makeColumn(card, numColumn);
        }
    }

    public int[] getIndexOtherCard(Card card, Card[] column){
        int[] indexTab=new int[2];
        int index=0;
        for (int i =0; i<column.length;i++){
            if(knownHand.getValeur(column[i])==knownHand.getValeur(card)){
                continue;
            }
            indexTab[index]=i;
            index+=1;
        }
        return indexTab;
    } 

    public int chooseBestIndex(int[] index, Card[] column){
        Card card1 = column[index[0]];
        Card card2 = column[index[1]];
        if (card1==null && card2==null){
            return index[0];
        }
        else if(card1==null || knownHand.getValeur(card2) > knownHand.getValeur(card1)){
            return index[1];
        }
        else if(card2==null || knownHand.getValeur(card1)>knownHand.getValeur(card2)){
            return index[0];
        }
        else{
            return -1; 
        }
    }

    

    public int whereEmptyColumn(){
        for (int i =0; i<knownHand.size(); i++){
            if (knownHand.nbKnownCard(knownHand.get(i))==3){
                return i; 
            }
        }
        return -1;
    }

    public void chooseWhereToReplace(Card card){
        if (whereEmptyColumn() != -1){
            int i = whereEmptyColumn();
            int j=rd.nextInt(0,knownHand.get(i).length);
            replaceCard(i, j, card);
        }
        else if(knownHand.nbKnownCard()<8   || knownHand.getNbPoint()<10){
            replaceWhereNull(card);
        }
        else{
            replaceHighCard(card);
        }
    }

    

    public void replaceWhereNull(Card card){
        int i;
        int j;
        do{
            i =rd.nextInt(0,knownHand.size());
            j=rd.nextInt(0,knownHand.get(0).length);
        }while(knownHand.get(i)[j] != null);
        replaceCard(i, j, card);
    }

    public void replaceHighCard(Card card){
        int i=getIndexHighCard();
        int j = getIndexHighCardColumn(knownHand.get(i));
        replaceCard(i, j, card);
    }

    public int getIndexHighCard(){
        int maxIndex=0;
        Card highCard=knownHand.get(0)[getIndexHighCardColumn(knownHand.get(0))];
        for (int i=1; i<knownHand.size();i++){
            Card currCard=knownHand.get(i)[getIndexHighCardColumn(knownHand.get(i))];
            if (knownHand.getValeur(currCard) > knownHand.getValeur(highCard)){
                highCard=currCard;
                maxIndex=i;
            }
        }
        return maxIndex;
        
    }

    public int getIndexHighCardColumn(Card[] column){
        Card highCard= column[0];
        int maxIndex=0;
        for (int i=1; i<column.length; i++){
            if (knownHand.getValeur(column[i])> knownHand.getValeur(highCard) && knownHand.cardOccurenceColumn(column[i], column ) == 1){
                highCard=column[i];
                maxIndex=i;

            }
        }
        return maxIndex;
    }

    public void revealCard(){
        int i;
        int j;
        do {
            i =rd.nextInt(0,knownHand.size());
            j=rd.nextInt(0,knownHand.get(0).length);
        }while(knownHand.get(i)[j] !=null);
        knownHand.get(i)[j]=hand.getCard(i, j);
    }

    public void jouer(){
        knownHand.showHand();
        chooseKeepOrNot(poubelle.pop(), true);
        knownHand.showHand();
    }



}
