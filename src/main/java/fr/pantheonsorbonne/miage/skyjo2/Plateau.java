package fr.pantheonsorbonne.miage.skyjo2; 

import java.util.ArrayList;
import java.util.List;

public class Plateau extends PlateauFacade {

    public Plateau(){
        super();
        playPlateau();
        //if(j2.knownHand.nbKnownCard() == 12 && j1.knownHand.nbKnownCard() != 12){
            //j1.jouer();
            //dernier tour pour le joueur intelligent 
        //}

        //pour le joueur intelligent dès que nb carte connu adv = 11 joueur en mode derniers tours
    }

    public List<Player> makListPlayers(int nbJoueur){
        List<Player> players = new ArrayList<>();
        for (int i=0; i<nbJoueur;i++){
            players.add(new DumbPlayer(deck, poubelle));
        }
        return players;

    }




    public static void main(String[] args){

        Plateau p=new Plateau();
        
    }

}
