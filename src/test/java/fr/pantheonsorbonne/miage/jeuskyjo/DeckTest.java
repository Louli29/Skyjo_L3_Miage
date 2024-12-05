package fr.pantheonsorbonne.miage.jeuskyjo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void verifierTotalCartePioche() {
        assertEquals(150, Deck.makePioche().size());

    }

    /*
     * @Test
     * void verifierNombreCarteParValeur(){
     * Deck deck = new Deck();
     * assertEquals(5, Deck.compterCarteParValeur(-2));
     * assertEquals(15, Deck.compterCarteParValeur(0));
     * assertEquals(10, Deck.compterCarteParValeur(-1));
     * assertEquals(10, Deck.compterCarteParValeur(1));
     * assertEquals(10, Deck.compterCarteParValeur(2));
     * assertEquals(10, Deck.compterCarteParValeur(3));
     * assertEquals(10, Deck.compterCarteParValeur(4));
     * assertEquals(10, Deck.compterCarteParValeur(5));
     * assertEquals(10, Deck.compterCarteParValeur(6));
     * assertEquals(10, Deck.compterCarteParValeur(7));
     * assertEquals(10, Deck.compterCarteParValeur(8));
     * assertEquals(10, Deck.compterCarteParValeur(9));
     * assertEquals(10, Deck.compterCarteParValeur(10));
     * assertEquals(10, Deck.compterCarteParValeur(11));
     * assertEquals(10, Deck.compterCarteParValeur(12));
     * }
     */

    @Test
    void verifierNombreCarteParValeurMoinsDeux() {
        Deck deck = new Deck();
        assertEquals(5, Deck.compterCarteParValeur(-2));
    }

    @Test
    void verifierNombreCarteParValeurZero() {
        Deck deck = new Deck();
        assertEquals(15, Deck.compterCarteParValeur(0));
    }

    @Test
    void verifierNombreCarteParValeurMoinsUn() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(-1));
    }

    @Test
    void verifierNombreCarteParValeurUn() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(1));
    }

    @Test
    void verifierNombreCarteParValeurDeux() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(2));
    }

    @Test
    void verifierNombreCarteParValeurTrois() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(3));
    }

    @Test
    void verifierNombreCarteParValeurQuatre() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(4));
    }

    @Test
    void verifierNombreCarteParValeurCinq() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(5));
    }

    @Test
    void verifierNombreCarteParValeurSix() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(6));
    }

    @Test
    void verifierNombreCarteParValeurSept() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(7));
    }

    @Test
    void verifierNombreCarteParValeurHuit() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(8));
    }

    @Test
    void verifierNombreCarteParValeurNeuf() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(9));
    }

    @Test
    void verifierNombreCarteParValeurDix() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(10));
    }

    @Test
    void verifierNombreCarteParValeurOnze() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(11));
    }

    @Test
    void verifierNombreCarteParValeurDouze() {
        Deck deck = new Deck();
        assertEquals(10, Deck.compterCarteParValeur(12));
    }

    @Test
    void verifierPiocheRenvoieCarte() {
        assertNotNull(Deck.piocher());
    }

    @Test
    void verifierCarteAbsenteDePioche() {
        
    }

}
