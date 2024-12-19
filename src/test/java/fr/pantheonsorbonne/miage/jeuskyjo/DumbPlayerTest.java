package fr.pantheonsorbonne.miage.jeuskyjo;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.skyjo2.Deck;
import fr.pantheonsorbonne.miage.skyjo2.DumbPlayer;
import fr.pantheonsorbonne.miage.skyjo2.Hand;
import fr.pantheonsorbonne.miage.skyjo2.Valeur;
import fr.pantheonsorbonne.miage.skyjo2.Card;
import fr.pantheonsorbonne.miage.skyjo2.Player;
import fr.pantheonsorbonne.miage.skyjo2.PlayerGuest;
import fr.pantheonsorbonne.miage.skyjo2.KnownHand;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DumbPlayerTest {

    @Test
    public void testChooseKeepOrNotReplaceCard() {
        DumbPlayer player = new DumbPlayer("Test");
        Card card = new Card(Valeur.CINQ);
        boolean isFromTrash = true;
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        player.setPlayerRound(hand);
        player.chooseKeepOrNot(card, isFromTrash);
        assertNotNull(player.getTrash());
    }

    @Test
    public void testChooseWhereToReplace() {
        DumbPlayer player = new DumbPlayer("Test");
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        player.setPlayerRound(hand);
        Card cardReplace = new Card(Valeur.UN);
        player.chooseWhereToReplace(cardReplace);
        assertNotNull(player.getknownHand().get(0)[0]);
    }

    @Test
    public void testWhereIsColumn() {
        DumbPlayer player = new DumbPlayer("TestPlayer");
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        player.setPlayerRound(hand);
        Card[] column1 = { new Card(Valeur.UN), new Card(Valeur.UN), new Card(Valeur.UN) };
        player.getknownHand().get(0)[0] = column1[0];
        player.getknownHand().get(0)[1] = column1[1];
        player.getknownHand().get(0)[2] = column1[2];
        int columnIndex = player.whereIsColumn();
        assertEquals(0, columnIndex);
    }

}
