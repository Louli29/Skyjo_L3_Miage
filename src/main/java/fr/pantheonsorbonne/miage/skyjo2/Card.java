package fr.pantheonsorbonne.miage.skyjo2;

public class Card {

    private Valeur valeur;
    public Object getValeur;

    public Card(Valeur valeur){
        this.valeur = valeur;
    }

    public int getValeur(){
        return this.valeur.getValeur();
    }

    public String toString(){
        return valeur.toString();
    }

}
