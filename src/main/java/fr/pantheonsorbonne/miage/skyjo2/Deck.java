package fr.pantheonsorbonne.miage.skyjo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<SkyjoCard> pioche;
    private final int nbMoinsDeux = 5;
    private final int nbZero = 15;
    private final int nbCarteClassique = 10;
    
    public Deck(){
        this.pioche=makePioche();
        Collections.shuffle(pioche);
    }

    public List<SkyjoCard> makePioche(){
        List<SkyjoCard> pioche = new ArrayList<>();
        for (Valeur val : Valeur.values()){
            if (val.getValeur()==-2){
                for (int i=0; i<nbMoinsDeux; i++){
                    pioche.add(new SkyjoCard(val));
                }
            }
            else if (val.getValeur()==0){
                for (int i=0; i<nbZero; i++){
                    pioche.add(new SkyjoCard(val));
                }
            }
            else{
                for (int i=0; i<nbCarteClassique; i++){
                    pioche.add(new SkyjoCard(val));
                }
            }
        }
        return pioche;

    }

    public SkyjoCard piocher(){
        SkyjoCard card=pioche.get(0);
        pioche.remove(0);
        return  card ; 
    }

    public int getMediane(){//ne fonctionne pas pour l'instant
        int medCard=pioche.size()/2;
        pioche.sort(null);
        SkyjoCard med = pioche.get(medCard);
        Collections.shuffle(pioche);
        return med.getValeur();
    }

}
