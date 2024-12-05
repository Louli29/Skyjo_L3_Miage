package fr.pantheonsorbonne.miage.jeuskyjo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Joueur {
    List<SkyjoCard[]> main=new ArrayList<>();
    List<boolean[]> mainConnu=new ArrayList<>();
    Random rd = new Random();


    public Joueur(Deck d){
        this.main=faireMain(d);
        this.mainConnu=initialiserMainConnu();
        System.out.println(nbCarteConnue());
        
    }

    public List<SkyjoCard[]> faireMain(Deck d){
        List<SkyjoCard[]> main = new ArrayList<>();
        for (int i=0; i<4;i++){
            SkyjoCard[] colonne=new SkyjoCard[3];
            for(int j=0; j<3; j++){
                colonne[j]=d.piocher();
            }
        main.add(i,colonne);
        }
        return main; 
    }
        
    public List<boolean[]> initialiserMainConnu() {
        List<boolean[]> mainConnu = new ArrayList<>();
        for (int i=0; i<4;i++){
            boolean[] colonne=new boolean[3];
            mainConnu.add(i,colonne);
        }
        for (int k=0;k<2;k++){
            int i= rd.nextInt(0,4);
            int j= rd.nextInt(0,3);
            mainConnu.get(i)[j]=true;
        }
        return mainConnu; 
    }

    public int nbCarteConnue(){
        int nb=0;
        for (boolean[] colonne : mainConnu){
            for (boolean estConnu : colonne){
                if (estConnu){
                    nb+=1;
                }
            }
        }
        return nb;
    }
    
    
    public void rendreVisibleCarte(int i, int j){
        this.mainConnu.get(i)[j]=true;
    }

    public SkyjoCard remplacerCarte(int i, int j, SkyjoCard carteRemplacante){
        SkyjoCard carteAJeter =this.main.get(i)[j];
        this.main.get(i)[j]=carteRemplacante;
        if(!mainConnu.get(i)[j]){
            this.mainConnu.get(i)[j]=true;
        }
        return carteAJeter;
    }
        
    


    
}
