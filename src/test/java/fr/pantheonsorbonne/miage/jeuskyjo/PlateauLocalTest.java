package fr.pantheonsorbonne.miage.jeuskyjo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.skyjo2.PlateauLocal;
import fr.pantheonsorbonne.miage.skyjo2.Player;
import fr.pantheonsorbonne.miage.skyjo2.SmartPlayer;

public class PlateauLocalTest {

    @Test
    public void testMakeListPlayers() {
        PlateauLocal plateau = new PlateauLocal();
        List<Player> players = plateau.makeListPlayers(3);
        assertEquals(3, players.size());
        assertEquals("player 0", players.get(0).getName());
        assertEquals("player 1", players.get(1).getName());
        assertEquals("player 2", players.get(2).getName());
    }

    @Test
    public void testGetScore() {
        PlateauLocal plateau = new PlateauLocal();
        Player player = new SmartPlayer("TestPlayer");
        player.addToScore(15);
        int score = plateau.getScore(player);
        assertEquals(15, score);
    }

}
