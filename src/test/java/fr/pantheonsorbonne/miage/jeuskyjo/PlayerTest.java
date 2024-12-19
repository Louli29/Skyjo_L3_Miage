package fr.pantheonsorbonne.miage.jeuskyjo;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.skyjo2.Deck;
import fr.pantheonsorbonne.miage.skyjo2.Hand;
import fr.pantheonsorbonne.miage.skyjo2.Player;
import fr.pantheonsorbonne.miage.skyjo2.Valeur;
import fr.pantheonsorbonne.miage.skyjo2.Card;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTest {

    private static class TestPlayer extends Player {
        public TestPlayer(String name) {
            super(name);
        }

        @Override
        public void chooseKeepOrNot(Card card, boolean isFromTrash) {
        }

        @Override
        public void chooseWhereToReplace(Card card) {
        }

        @Override
        public void revealCard() {
        }

        @Override
        public void jouer() {
        }
    }

    @Test
    public void testName() {
        Player player = new TestPlayer("Test");
        String name = player.getName();
        assertEquals("Test", name);
    }

    @Test
    public void testSetPlayerRound() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        Player player = new TestPlayer("Test");
        player.setPlayerRound(hand);
        assertNotNull(player.getknownHand());
        assertEquals(hand, player.getHand());
    }

    @Test
    public void testSetPlayerTour() {
        Player player = new TestPlayer("Test");
        Card[] deckAndTrash = { new Card(Valeur.UN), new Card(Valeur.DEUX) };
        player.setPlayerTour(deckAndTrash);

        assertEquals(1, player.getDeck().getValeur());
        assertEquals(2, player.getTrash().getValeur());
    }

    @Test
    public void testAddToScore() {
        Player player = new TestPlayer("Test");
        player.addToScore(10);
        assertEquals(10, player.getScore());
    }

    @Test
    public void testDeleteColumn() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        Player player = new TestPlayer("Test");
        player.setPlayerRound(hand);
        player.deleteColumn(0);
        assertEquals(3, hand.getHand().size());
        assertEquals(3, player.knownHand.size());
    }

    @Test
    public void testCountKnownPoint() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.UN);
        }
        Hand hand = new Hand(cards);
        Player player = new TestPlayer("Test");
        player.setPlayerRound(hand);
        int points = player.countKnownPoint();
        assertEquals(2, points);
    }
}
