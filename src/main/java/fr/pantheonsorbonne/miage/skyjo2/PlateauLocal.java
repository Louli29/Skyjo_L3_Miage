package fr.pantheonsorbonne.miage.skyjo2; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PlateauLocal extends PlateauFacade {
    static Deque<Card> poubelle ;
    static Deck deck; 
    

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

    public boolean isOverRound(){
        for(Player player : players){
            if(player.knownHand.nbKnownCard() == 12){
                return true;
            }
        }
        return false;
    }

    public void playRound(){
        setFirstPlayer();
        poubelle.push(deck.piocher());
        while (!isOverRound()) {
            for(Player player : players){
                System.out.println("\n"+"C'est au tour de "+player.getName()+"\n");
                Card[] deckAndTrash={deck.piocher(),poubelle.pop()};
                player.setPlayerTour(deckAndTrash);
                player.jouer();
                poubelle.push(player.getTrash());
            }
        }
    }
    public Card[] makeListCard(){
        Card[] listCard=new Card[12];
        for(int i=0; i<listCard.length;i++){
            listCard[i]=deck.piocher();
        }
        return listCard;
    }

    public void playGame(){
        while (!isOverGame()){
            PlateauLocal.poubelle = new LinkedList<Card>() ;
            PlateauLocal.deck=new Deck(poubelle);
            for(Player player:players){
                Card[] listCard=makeListCard();
                giveCardsToPlayer(player,listCard);
            }
            playRound();
            for(Player player:players){
                player.addToScore(player.hand.getNbPoint());
                System.out.println("\n"+player.getName());
                System.out.println("Le score de ta manche : "+player.hand.getNbPoint());
                System.out.println("Ton score totale : "+ player.getScore()+"\n");

            }

        }
    }

    

    public static void main(String[] args){

        PlateauLocal plateau=new PlateauLocal();
        plateau.playGame();
        
    }

}
