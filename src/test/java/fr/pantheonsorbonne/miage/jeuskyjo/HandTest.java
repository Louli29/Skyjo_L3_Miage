package fr.pantheonsorbonne.miage.jeuskyjo;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.skyjo2.Deck;
import fr.pantheonsorbonne.miage.skyjo2.Hand;
import fr.pantheonsorbonne.miage.skyjo2.Valeur;
import fr.pantheonsorbonne.miage.skyjo2.Card;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HandTest {

    @Test
    public void testMakeHandSize() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        int columns = hand.getHand().size();
        assertEquals(4, columns);
    }

    @Test
    public void testGetCard() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.UN);
        }
        Hand hand = new Hand(cards);
        Card card = hand.getCard(0, 0);
        assertNotNull(card);
        assertEquals(1, card.getValeur());
    }

    @Test
    public void testDeleteColumn() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        hand.deleteColumn(0);
        int columns = hand.getHand().size();
        assertEquals(3, columns);
    }

    @Test
    public void testRemplacerCarte() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
            Hand hand = new Hand(cards);
            Card newCard = new Card(Valeur.DEUX);
            Card oldCard = hand.remplacerCarte(0, 0, newCard);
            assertNotNull(oldCard);
            assertEquals(0, oldCard.getValeur());
            assertEquals(2, hand.getCard(0, 0).getValeur());
        }
    }

    @Test
    public void testGetNbPoint() {
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.UN);
        }
        Hand hand = new Hand(cards);
        int points = hand.getNbPoint();
        assertEquals(12, points);
    }
}
