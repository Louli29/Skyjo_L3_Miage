package fr.pantheonsorbonne.miage.skyjo2;

public class SmartPlayer extends Player{

    public SmartPlayer(Deck d,Poubelle poubelle){
        super(d, poubelle);
    }

    public void chooseKeepOrNot(SkyjoCard card, boolean isFromTrash){//card récup de la poubelle donnée par le plateau attention bien l'enlever de la poubelle
        System.out.println(" ");
        System.out.println(card.toString());   
        System.out.println(" "); 

        if (card.getValeur()<=0){
                chooseWhereToReplace(card);
        }

        else if (knownHand.getIndexColumnSameCard(card) != -1 ){
            System.out.println("on essaie de faire une colonne");
            int numColumn = knownHand.getIndexColumnSameCard(card);
            if(knownHand.cardOccurenceColumn(card,knownHand.get(numColumn) ) == 2){
                deleteColumn(card, numColumn);
            }
            else{
                System.out.println("tryMakeColumn");
                tryMakeColumn(card,numColumn,isFromTrash);
            }
        }
        else if(card.getValeur()<5){
            chooseWhereToReplace(card);
        }
        else{
            poubelle.addCard(card);
            if(isFromTrash){
                chooseKeepOrNot(d.piocher(),false);
            }
            else{
                revealCard();
            }
            
        }

    }

    public void makeColumn(SkyjoCard card ,int numColumn){ 
        SkyjoCard[] column=knownHand.get(numColumn);
        int[] indexOthersCards=getIndexOtherCard(card,column);
        int bestIndex=chooseBestIndex(indexOthersCards,column);
        if(bestIndex==-1 && knownHand.getValeur(column[indexOthersCards[0]])<card.getValeur()){
            poubelle.addCard(card);
            chooseKeepOrNot(d.piocher(),false);
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
                poubelle.addCard(card);
                chooseKeepOrNot(d.piocher(),false);
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
            if(column[i].getValeur()==card.getValeur()){
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
        else if(card1==null || card2.getValeur() > card1.getValeur()){
            return index[1];
        }
        else if(card2==null || card1.getValeur()>card2.getValeur()){
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

    public void chooseWhereToReplace(SkyjoCard card){//très bof ... améliorer pour <=0
        if (whereEmptyColumn() == -1){
             //si il reste plus de 5 carte pas connu remplacer là ou s'est vide sinon 
             //sinon remplacer la plus grande carte pour trouver la plus grande carte trouver l'index de la plus grande carte par colonne puis la plus grande  
        }
        else{
            revealCard(); 
        }
    }

    public void revealCard(){//version dumb
        int i;
        int j;
        do {
            i =rd.nextInt(0,knownHand.size());
            j=rd.nextInt(0,knownHand.get(0).length);
        }while(knownHand.get(i)[j]==null);
        knownHand.get(i)[j]=hand.getCard(i, j);//mettre les memes méthodes pour hand et knownhand et faire de l'héritage
    }

    public void jouer(){
        knownHand.showHand();
        chooseKeepOrNot(poubelle.getLastCard(), true);
        knownHand.showHand();
    }



}
