package fr.pantheonsorbonne.miage.jeuskyjo;

public class Plateau {
    private SkyjoCard poubelle;

    public SkyjoCard getPoubelle() {
        return poubelle;
    }


    public void jeter(SkyjoCard carteAJeter) {
        this.poubelle=carteAJeter;
    }

}
