package fr.pantheonsorbonne.miage.skyjo2;

import java.util.List;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;

public class PlateauHost extends PlateauFacade {

    private final HostFacade hostFacade;

    public PlateauHost(HostFacade hostFacade){
        super();
        this.hostFacade = hostFacade;
    }

    public static void main(String[] args){

        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();
        
        //set the name of the player
        hostFacade.createNewPlayer("Host");

        //create a new game of war
        fr.pantheonsorbonne.miage.model.Game skyjo = hostFacade.createNewGame("SKYJO");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(nbJoueur);

        PlateauFacade host = new PlateauHost(hostFacade);
        host.playPlateau();
        System.exit(0);
        
    }

    @Override
    public List<Player> makeListPlayers(int nbJoueur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makListPlayers'");
    }

    
}
