package fr.pantheonsorbonne.miage.skyjo2;
public class Plateau {
    static DumbPlayer j1;
    static DumbPlayer j2;
    static Deck deck; 
    static Poubelle poubelle;

   

    public Plateau(){
        Plateau.deck=new Deck();
        Plateau.poubelle=new Poubelle(deck);
        Plateau.j1=new DumbPlayer(deck, poubelle); //faire une boucle avec une variable nb de joueur
        Plateau.j2=new DumbPlayer(deck, poubelle);
        
        int i=0;
        while(i<55){
            j1.jouer();
            System.out.println("  ");
            //j2.jouer();
            i++;
        }
        System.out.println(i);
        if(j2.knownHand.nbKnownCard() == 12 && j1.knownHand.nbKnownCard() != 12){
            j1.jouer();
            //dernier tour pour le joueur intelligent 
        }

        //pour le joueur intelligent dès que nb carte connu adv = 11 joueur en mode derniers tours
        
    }




    public static void main(String[] args){

        Plateau p=new Plateau();
        
    }

}
