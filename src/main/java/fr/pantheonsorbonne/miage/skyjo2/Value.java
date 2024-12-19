package fr.pantheonsorbonne.miage.skyjo2;

public enum Value {
    DEUX_NEG(-2),
    UN_NEG(-1),
    ZERO(0),
    UN(1),
    DEUX(2),
    TROIS(3),
    QUATRE(4),
    CINQ(5),
    SIX(6),
    SEPT(7),
    HUIT(8),
    NEUF(9),
    DIX(10),
    ONZE(11),
    DOUZE(12);


    private final int val;

    private Value(int valeur) {
        this.val= valeur;
    }

    public int getValeur() {
        return this.val;
    }

    

}
