package fr.pantheonsorbonne.miage.skyjo2; 

import java.util.ArrayList;
import java.util.List;

public class PlateauLocal extends PlateauFacade {

    public PlateauLocal(){ //il faut dire qui gagne et qui commence et bien faire jouer le dernier tour
        super();
        //if(j2.knownHand.nbKnownCard() == 12 && j1.knownHand.nbKnownCard() != 12){
            //j1.jouer();
            //dernier tour pour le joueur intelligent 
        //}

        //pour le joueur intelligent dès que nb carte connu adv = 11 joueur en mode derniers tours
    }

    public List<Player> makeListPlayers(int nbJoueur){
        List<Player> players = new ArrayList<>();
        //for (int i=0; i<nbJoueur;i++){
            //players.add(new DumbPlayer(deck, poubelle));
        //}
        players.add(new DumbPlayer(deck, poubelle));
        players.add(new SmartPlayer(deck, poubelle));
        return players;

    }




    public static void main(String[] args){

        PlateauLocal p=new PlateauLocal();
        p.playPlateau();
        
    }

}
