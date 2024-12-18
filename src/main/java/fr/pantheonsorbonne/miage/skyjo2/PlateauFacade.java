package fr.pantheonsorbonne.miage.skyjo2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public abstract class PlateauFacade {//ajouter déclarer un gagnant
    static Deck deck; 
    static Deque<Card> poubelle ;
    final List<Player> players;
    final static int nbJoueur=2;

    protected PlateauFacade(){
        PlateauLocal.deck=new Deck();
        PlateauLocal.poubelle=new LinkedList<Card>();

        poubelle.push(deck.piocher());
        this.players=makeListPlayers(nbJoueur);
        
    }    

        
    public void playRound() {

        while (isOverRound()==false) {
            for(Player player : players){
                System.out.println("\n"+"C'est au tour de "+player.getName()+"\n");
                player.jouer();
            }
        }
        for(Player player : players){
            player.addToScore(player.hand.getNbPoint());
            System.out.println("\n"+player.getName());
            System.out.println("Le score de ta manche : "+player.hand.getNbPoint());
            System.out.println("Ton score totale : "+ player.getScore()+"\n");
        }
        
    }

    public void playGame(){
        while (!isOverGame()){
            playRound();//a la place à chque fois recréer un round avec la même liste de joueur et faire round.playRoind()

            for(Player player : players){
                player.addToScore(player.hand.getNbPoint());
                System.out.println("\n"+player.getName());
                System.out.println("Le score de ta manche : "+player.hand.getNbPoint());
                System.out.println("Ton score totale : "+ player.getScore()+"\n");
            }
        }
    }

    public boolean isOverGame(){
        for(Player player : players){
            if(player.getScore() >= 100){
                System.out.println(player.getName()+"Tu as perdu");
                return true;
            }
        }
        return false;

    }
    
    public boolean isOverRound(){
        for(Player player : players){
            if(player.knownHand.nbKnownCard() == 12){
                return true;
            }
        }
        return false;
    }


    public abstract List<Player> makeListPlayers(int nbJoueur);


}
