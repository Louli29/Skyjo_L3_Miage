package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public abstract class PlateauFacade {
    protected static Deck deck; 
    protected static Deque<Card> trash ;
    List<Player> players;
    final static int nbPlayers=2;
    

    protected PlateauFacade(){
        
        this.players=makeListPlayers(nbPlayers);
    } 
    
    public void play(){
        //tout refaire
    }

    public void playGame(){
        while (!isOverGame()){
            trash = new LinkedList<Card>() ;
            deck=new Deck(trash);
            for(Player player:players){
                Card[] listCard=makeListCard();
                giveCardsToPlayer(player,listCard);
            }
            playRound();
            for(Player player:players){ //modi cette partie bc get score doit être un truc def dans les enfants
                player.addToScore(player.hand.getNbPoint());
                System.out.println("\n"+player.getName());
                System.out.println("Le score de ta manche : "+player.hand.getNbPoint());
                System.out.println("Ton score totale : "+ player.getScore()+"\n");

            }

        }
    }

    public Card[] makeListCard(){
        Card[] listCard=new Card[12];
        for(int i=0; i<listCard.length;i++){
            listCard[i]=deck.piocher();
        }
        return listCard;
    }

    public boolean isOverGame() {
        for(Player player : players){
            if(getScore(player) >= 100){
                System.out.println(player.getName()+" Tu as perdu");
                return true;
            }
        }
        return false;
    }

    public boolean isOverRound(){
        for(Player player : players){
            if(getNbKnownCard(player) == 12){
                return true;
            }
        }
        return false;
    }
    
    
    public abstract void playRound();

    public abstract List<Player> makeListPlayers(int nbJoueur);

    protected abstract void giveCardsToPlayer(Player player, Card[] hand);

    public abstract int getScore(Player player);

    public abstract int getNbKnownCard(Player player);




}
