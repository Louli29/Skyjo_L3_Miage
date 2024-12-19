package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Random;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class FacadeGuest {
    static final String playerId = "Player-" + new Random().nextInt();
    //static final Deque<SkyjoCardCard> hand = new LinkedList<>(); idk si c'est utile
    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game skyjo;


    public static void main(String[] args) {

        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        skyjo = playerFacade.autoJoinGame("SKYJO");
        while (true) {

            GameCommand command = playerFacade.receiveGameCommand(skyjo);
            switch (command.name()) {
                case "play":
                    System.out.println("It's your turn to play");
                    play(command);
                    break;
                case "gameOver":
                    handleGameOverCommand(command);
                    break;

            }
        }
    }

    private static void play(GameCommand command) { 
        if (command.params().get("playerId").equals(playerId)) {
            
        }
    }

    private static void handleGameOverCommand(GameCommand command) {
        if (command.body().equals("win")) {
            System.out.println("I've won!");
        } else {
            System.out.println("I've lost :-(");
        }
        System.exit(0);
    }
}
