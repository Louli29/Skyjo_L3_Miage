package fr.pantheonsorbonne.miage.skyjo2;

import java.util.List;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class PlateauHost extends PlateauFacade {

    private final HostFacade hostFacade;
    private final Game skyjo;

    public PlateauHost(HostFacade hostFacade, fr.pantheonsorbonne.miage.model.Game skyjo){
        super();
        this.hostFacade = hostFacade;
        this.skyjo = skyjo; 
    }

    public static void main(String[] args){

        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();
        
        hostFacade.createNewPlayer("Host");

        fr.pantheonsorbonne.miage.model.Game skyjo = hostFacade.createNewGame("SKYJO");

        hostFacade.waitForExtraPlayerCount(nbJoueur);

        PlateauFacade host = new PlateauHost(hostFacade, skyjo);
        host.playPlateau();
        System.exit(0);
        
    }

    @Override
    public List<Player> makeListPlayers(int nbJoueur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makListPlayers'");
    }

    protected void declareWinner(String winner) {
        hostFacade.sendGameCommandToPlayer(skyjo, winner, new GameCommand("gameOver", "win"));
    }

    
}
