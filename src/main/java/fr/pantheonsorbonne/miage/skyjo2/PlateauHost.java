package fr.pantheonsorbonne.miage.skyjo2;

import java.util.ArrayList;
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

        hostFacade.waitForExtraPlayerCount(nbPlayers);

        PlateauFacade host = new PlateauHost(hostFacade, skyjo);
        host.playGame();
        System.exit(0);
        
    }

    @Override
    public List<Player> makeListPlayers(int nbJoueur) {
        List<String> playersStr = new ArrayList<>(this.skyjo.getPlayers());
        List<Player> players = new ArrayList<>();
        for(String player:playersStr){
            players.add(new PlayerGuest(player));
        }
        return players;
    }

    protected void declareWinner(String winner) {
        hostFacade.sendGameCommandToPlayer(skyjo, winner, new GameCommand("gameOver", "win"));
    }

    public void giveDeckAndTrask(Player player){
        Card[] deckAndTrash={deck.piocher(),trash.pop()};
        String hand = Card.cardsToString(deckAndTrash);
        hostFacade.sendGameCommandToPlayer(skyjo, player.getName(), new GameCommand("DeckAndTrashForYou", (String)hand));
    }

    public void getBackDeckAndTrash(Player player){
        //comment récupérer un tableau 
    }

    @Override
    public void playRound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playRound'");
    }

    @Override
    protected void giveCardsToPlayer(Player player, Card[] cards) {
        String hand = Card.cardsToString(cards);
        hostFacade.sendGameCommandToPlayer(skyjo, player.getName(), new GameCommand("cardsForYou", (String)hand));
    }

    @Override
    public int getScore(Player player) {
        hostFacade.sendGameCommandToPlayer(skyjo, player.getName(), new GameCommand("nbPoint"));
        GameCommand expectedInt = hostFacade.receiveGameCommand(skyjo);
        String res = expectedInt.body();
        int nb = Integer.parseInt(res);
        return nb;
        
    }

    @Override
    public int getNbKnownCard(Player player){
        hostFacade.sendGameCommandToPlayer(skyjo, player.getName(), new GameCommand("nbKnownCard"));
        GameCommand expectedInt = hostFacade.receiveGameCommand(skyjo);
        String res = expectedInt.body();
        int nb = Integer.parseInt(res);
        return nb;
        
    }

    
}
