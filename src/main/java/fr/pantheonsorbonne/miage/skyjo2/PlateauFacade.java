package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Deque;
import java.util.List;

public abstract class PlateauFacade {
    static Deck deck; 
    static Deque<Card> poubelle ;
    List<Player> players;
    final static int nbJoueur=2;
    

    protected PlateauFacade(){
        
        this.players=makeListPlayers(nbJoueur);
    } 
    
    public void play(){
        //tout refaire
    }

    

    public boolean isOverGame(){
        for(Player player : players){
            if(player.getScore() >= 100){
                System.out.println(player.getName()+" Tu as perdu");
                return true;
            }
        }
        return false;

    }

    
    
    


    public abstract List<Player> makeListPlayers(int nbJoueur);

    protected abstract void giveCardsToPlayer(Player player, Card[] hand);


}
