package fr.pantheonsorbonne.miage.jeuskyjo;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.skyjo2.Deck;
import fr.pantheonsorbonne.miage.skyjo2.Hand;
import fr.pantheonsorbonne.miage.skyjo2.KnownHand;
import fr.pantheonsorbonne.miage.skyjo2.Valeur;
import fr.pantheonsorbonne.miage.skyjo2.Card;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class KnownHandTest {

    @Test
    public void testMakeKnownHand() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        KnownHand knownHand = new KnownHand(hand);
        assertEquals(4, knownHand.size());
        assertNotNull(knownHand.get(0)[0]);
        assertNotNull(knownHand.get(1)[1]);
        assertNull(knownHand.get(2)[2]);
    }

    @Test
    public void testNbKnownCard() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.UN);
        }
        Hand hand = new Hand(cards);
        KnownHand knownHand = new KnownHand(hand);
        int nbKnown = knownHand.nbKnownCard();
        assertEquals(2, nbKnown);
    }

    @Test
    public void testRemoveColumn() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.DEUX);
        }
        Hand hand = new Hand(cards);
        KnownHand knownHand = new KnownHand(hand);
        knownHand.remove(0);
        assertEquals(3, knownHand.size());
    }

    @Test
    public void testGetIndexColumnSameCard() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        KnownHand knownHand = new KnownHand(hand);
        Card targetCard = new Card(Valeur.ZERO);
        int index = knownHand.getIndexColumnSameCard(targetCard);
        assertTrue(index >= 0 && index < 4);
    }

    @Test
    public void testCardOccurrenceColumn() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        KnownHand knownHand = new KnownHand(hand);
        Card[] column = knownHand.get(0);
        int occurrences = knownHand.cardOccurenceColumn(new Card(Valeur.ZERO), column);
        assertEquals(1, occurrences);
    }

    @Test
    public void testGetNbPoint() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.UN);
        }
        Hand hand = new Hand(cards);
        KnownHand knownHand = new KnownHand(hand);
        int points = knownHand.getNbPoint();
        assertEquals(2, points);
    }
}