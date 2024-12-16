package fr.pantheonsorbonne.miage.skyjo2;

import java.util.ArrayList;
import java.util.List;

public abstract class PlateauFacade {
    static Deck deck; 
    static Poubelle poubelle;
    final List<Player> players;
    final static int nbJoueur=2;

    protected PlateauFacade(){
        Plateau.deck=new Deck();
        Plateau.poubelle=new Poubelle(deck);
        this.players=makListPlayers(nbJoueur);
        
    }    

        
        public void playPlateau() {
        
        //boucle pour jouer
        while (isOver()==false) {
            for(Player player : players){
                System.out.println("  ");
                player.jouer();
            }

        }
        // afficher le gagnant 
        }
    public boolean isOver(){
        for(Player player : players){
            if(player.knownHand.nbKnownCard() == 12){
                return true;
            }
        }
        return false;
    }


    public abstract List<Player> makListPlayers(int nbJoueur);


}
