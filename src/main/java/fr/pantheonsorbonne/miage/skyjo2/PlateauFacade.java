package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public abstract class PlateauFacade {//ajouter déclarer un gagnant
    static Deck deck; 
    static Deque<SkyjoCard> poubelle ;
    final List<Player> players;
    final static int nbJoueur=2;

    protected PlateauFacade(){
        PlateauLocal.deck=new Deck();
        PlateauLocal.poubelle=new LinkedList<SkyjoCard>();

        poubelle.push(deck.piocher());
        this.players=makeListPlayers(nbJoueur);
        
    }    

        
    public void playPlateau() {
        while (isOver()==false) {
            for(Player player : players){
                System.out.println("  ");
                player.jouer();
            }

        }
        for(Player player : players){
            System.out.println(player.hand.getNbPoint());
        }
    }
    
    public boolean isOver(){
        for(Player player : players){
            if(player.knownHand.nbKnownCard() == 12){
                return true;
            }
        }
        return false;
    }


    public abstract List<Player> makeListPlayers(int nbJoueur);


}
