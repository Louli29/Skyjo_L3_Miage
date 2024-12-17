package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Deque;

public class SmartPlayer extends Player{

    public SmartPlayer(Deck d, Deque<SkyjoCard>  poubelle){
        super(d, poubelle);
    }

    public void chooseKeepOrNot(SkyjoCard card, boolean isFromTrash){
        System.out.println("Smart");
        System.out.println(" ");
        System.out.println(card.toString());   
        System.out.println(" "); 

        if (card.getValeur()<0){
                chooseWhereToReplace(card);
        }

        else if (knownHand.getIndexColumnSameCard(card) != -1 && card.getValeur()<9){
            int numColumn = knownHand.getIndexColumnSameCard(card);
            if(knownHand.cardOccurenceColumn(card,knownHand.get(numColumn) ) == 2){
                deleteColumn(card, numColumn);
            }
            else{
                tryMakeColumn(card,numColumn,isFromTrash);
            }
        }
        else if(card.getValeur()<4){
            chooseWhereToReplace(card);
        }
        else{
            poubelle.push(card);
            if(isFromTrash){
                chooseKeepOrNot(d.piocher(),false);
            }
            else{
                revealCard();
            }
            
        }

    }

    public void columnFail(SkyjoCard card){
        if (card.getValeur()<5){
            chooseWhereToReplace(card);
        }
        else{
            poubelle.push(card);
            chooseKeepOrNot(d.piocher(),false);
        }
    }

    public void makeColumn(SkyjoCard card ,int numColumn){ 
        SkyjoCard[] column=knownHand.get(numColumn);
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

    public void tryMakeColumn(SkyjoCard card, int numColumn, boolean isFromTrash){
        if (knownHand.nbKnownCard()>7 && card.getValeur()>5){
            if(isFromTrash){
                columnFail(card);
            }
            else{
                revealCard();
            }
            
        }
        else{
            makeColumn(card, numColumn);
        }
    }

    public int[] getIndexOtherCard(SkyjoCard card, SkyjoCard[] column){
        int[] indexTab=new int[2];
        int index=0;
        for (int i =0; i<column.length;i++){
            if(knownHand.getValeur(column[i])==knownHand.getValeur(card)){
                break;
            }
            indexTab[index]=i;
            index+=1;
        }
        return indexTab;
    } 

    public int chooseBestIndex(int[] index, SkyjoCard[] column){
        SkyjoCard card1 = column[index[0]];
        SkyjoCard card2 = column[index[1]];
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
            if (knownHand.nbKnownCard(knownHand.get(i))==0){
                return i; 
            }
        }
        return -1;
    }

    public void chooseWhereToReplace(SkyjoCard card){
        if (whereEmptyColumn() != -1){
            int i = whereEmptyColumn();
            int j=rd.nextInt(0,knownHand.get(i).length);
            replaceCard(i, j, card);
        }
        else if(knownHand.nbKnownCard()>8){
            replaceWhereNull(card);
        }
        else{
            System.out.println("try replace high card");
            replaceHighCard(card);
        }
    }

    public void replaceWhereNull(SkyjoCard card){
        int i;
        int j;
        do{
            i =rd.nextInt(0,knownHand.size());
            j=rd.nextInt(0,knownHand.get(0).length);
        }while(knownHand.get(i)[j]!=null);
        replaceCard(i, j, card);
    }

    public void replaceHighCard(SkyjoCard card){
        int i=getHighCard();
        int j = getHighCardColumn(knownHand.get(i));
        replaceCard(i, j, card);
    }

    public int getHighCard(){
        int maxIndex=0;
        SkyjoCard highCard=knownHand.get(0)[getHighCardColumn(knownHand.get(0))];
        for (int i=1; i<knownHand.size();i++){
            SkyjoCard currCard=knownHand.get(i)[getHighCardColumn(knownHand.get(i))];
            if (knownHand.getValeur(currCard) > knownHand.getValeur(highCard)){
                highCard=currCard;
                maxIndex=i;
            }
        }
        return maxIndex;
        
    }

    public int getHighCardColumn(SkyjoCard[] column){
        SkyjoCard highCard= column[0];
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
