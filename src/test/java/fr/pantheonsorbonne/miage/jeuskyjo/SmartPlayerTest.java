package fr.pantheonsorbonne.miage.jeuskyjo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.skyjo2.Card;
import fr.pantheonsorbonne.miage.skyjo2.Hand;
import fr.pantheonsorbonne.miage.skyjo2.SmartPlayer;
import fr.pantheonsorbonne.miage.skyjo2.Valeur;

public class SmartPlayerTest {

    @Test
    public void testRevealCard() {
        SmartPlayer player = new SmartPlayer("Test");
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        player.setPlayerRound(hand);
        player.revealCard();
        assertNotNull(player.getknownHand().get(0)[0]);
    }
 
    @Test
    public void testWhereEmptyColumn() {
        SmartPlayer player = new SmartPlayer("Test");
        Card[] cards = new Card[12];
        for (int i = 0; i < 12; i++) {
            cards[i] = new Card(Valeur.ZERO);
        }
        Hand hand = new Hand(cards);
        player.setPlayerRound(hand);

        int voidIndex = player.whereEmptyColumn();
        assertTrue(voidIndex == -1 || voidIndex >= 0);
    }

}
