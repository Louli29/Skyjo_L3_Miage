package fr.pantheonsorbonne.miage.skyjo2; 

import java.util.ArrayList;
import java.util.List;

public class PlateauLocal extends PlateauFacade {
    

    public PlateauLocal(){
        super();
        // qui commence et bien faire jouer le dernier tour 
        
    }

    public List<Player> makeListPlayers(int nbJoueur){
        List<Player> players = new ArrayList<>();
        for (int i=0; i<nbJoueur;i++){
            Hand smartHand= new Hand(deck);
            KnownHand smartKnownHand=new KnownHand(smartHand, poubelle);
            players.add(new SmartPlayer(deck, poubelle, "j"+i, smartHand, smartKnownHand));
        }
        return players;

    }




    public static void main(String[] args){

        PlateauLocal plateau=new PlateauLocal();
        plateau.playGame();
        
    }

}
