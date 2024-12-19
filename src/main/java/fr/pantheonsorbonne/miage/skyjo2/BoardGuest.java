package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Random;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class BoardGuest {
    static final String playerId = "Player-" + new Random().nextInt();
    static Hand hand ;
    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game skyjo;
    static Player playerG=new SmartPlayer(playerId);


    public static void main(String[] args) {

        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        skyjo = playerFacade.autoJoinGame("SKYJO");
        
        while (true) {

            GameCommand command = playerFacade.receiveGameCommand(skyjo);
            switch (command.name()) {
                case "cardsForYou":
                    handleCardsForYou(command);
                    break;
                case "deckAndTrashForYou":
                    System.out.println("It's your turn to play");
                    handleDeckAndTrash(command);
                    break;
                case "nbPoint":
                    getNbPoint(command);
                    break;
                case "score":
                    getScore(command);
                    break;
                case "nbKnownCard":
                    getNbKnownCard(command);
                    break;
                case "gameOver":
                    handleGameOverCommand(command);
                    break;

            }
        }
    }

    private static void handleDeckAndTrash(GameCommand command) { 
        Card[] deckAndTrash = Card.stringToCards(command.body());
        playerG.setPlayerTour(deckAndTrash);
        playerG.jouer();
        String remainingCards = Card.cardsToString(deckAndTrash);
        playerFacade.sendGameCommandToAll(skyjo, new GameCommand("deckAndTrash", remainingCards));
    }

    private static void handleGameOverCommand(GameCommand command) {
        if (command.body().equals("win")) {
            System.out.println("I've won!");
        } else {
            System.out.println("I've lost :-(");
        }
        System.exit(0);
    }

    public static void getNbPoint(GameCommand command){
        int nb =playerG.hand.getNbPoint();
        String point = String.valueOf(nb);
        playerFacade.sendGameCommandToAll(skyjo, new GameCommand("nbPoint", point));
    }

    public static void getScore(GameCommand command){
        int nb=playerG.getScore();
        String score = String.valueOf(nb);
        playerFacade.sendGameCommandToAll(skyjo, new GameCommand("score", score));
    }

    public static void getNbKnownCard(GameCommand command){
        int nb=playerG.knownHand.nbKnownCard();
        String nbKnownCard = String.valueOf(nb);
        playerFacade.sendGameCommandToAll(skyjo, new GameCommand("nbKnownCard", nbKnownCard));
    }



    private static void handleCardsForYou(GameCommand command) {
        Card[] listCard = Card.stringToCards(command.body());
        hand = new Hand(listCard);
        playerG.setPlayerRound(hand);
        

    }


}
