package fr.pantheonsorbonne.miage.skyjo2; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlateauLocal extends PlateauFacade {
    

    public PlateauLocal(){
        super();
        
    }

    public List<Player> makeListPlayers(int nbJoueur){
        List<Player> players = new ArrayList<>();
        for (int i=0; i<nbJoueur;i++){
            Player player=new SmartPlayer("player "+i);
            players.add(player);
            
        }
        return players;
    }
    
    @Override
    protected void giveCardsToPlayer(Player player, Card[] listCard) {
        player.setPlayerRound(new Hand(listCard));
    }

    public void setFirstPlayer(){
        Player firstPlayer = players.get(0);
        int index = 0;
        for(int i=1; i<players.size(); i++){
            Player currPlayer = players.get(i);
            if(currPlayer.getknownHand().getNbPoint()>firstPlayer.getknownHand().getNbPoint()){
                firstPlayer=currPlayer;
                index=i;
            }
        }
        Collections.swap(players, 0, index);
    }

    

    public void playRound(){
        setFirstPlayer();
        trash.push(deck.piocher());
        while (!isOverRound()) {
            for(Player player : players){
                System.out.println("\n"+"C'est au tour de "+player.getName()+"\n");
                Card[] deckAndTrash={deck.piocher(),trash.pop()};
                player.setPlayerTour(deckAndTrash);
                player.jouer();
                trash.push(player.getTrash());
            }
        }
    }


    @Override
    public int getScore(Player player) {
        return player.getScore();
    }

    @Override
    public int getNbKnownCard(Player player) {
        return player.knownHand.nbKnownCard();
    }

    public static void main(String[] args){

        PlateauLocal plateau=new PlateauLocal();
        plateau.playGame();
        
    }

}
