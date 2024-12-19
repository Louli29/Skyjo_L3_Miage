package fr.pantheonsorbonne.miage.jeuskyjo;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.skyjo2.Deck;
import fr.pantheonsorbonne.miage.skyjo2.Valeur;
import fr.pantheonsorbonne.miage.skyjo2.Card;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeckTest {

    @Test
    public void testMakePiocheSize() {
        Deque<Card> trash = new ArrayDeque<>();
        Deck deck = new Deck(trash);
        List<Card> pioche = deck.makePioche();
        int size = (5 * 1) + (15 * 1) + (10 * (Valeur.values().length - 2));
        assertEquals(size, pioche.size());
    }

    @Test
    public void testPiocherReducesDeckSize() {
        Deque<Card> trash = new ArrayDeque<>();
        Deck deck = new Deck(trash);
        int size = deck.makePioche().size();
        Card drawnCard = deck.piocher();
        int newSize = size - 1;
        assertNotNull(drawnCard);
        assertEquals(newSize, deck.makePioche().size() - 1);
    }

    @Test
    public void testInitialTrashIsEmpty() {
        Deque<Card> trash = new ArrayDeque<>();
        Deck deck = new Deck(trash);
        assertTrue(trash.isEmpty());
    }

}
