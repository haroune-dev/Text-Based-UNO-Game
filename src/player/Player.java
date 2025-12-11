package player;
import java.util.ArrayList;
import cards.*;
import enumTypes.Color;

public class Player {
    
    private String name;
    private int score;          
    private PlayerHand hand = new PlayerHand();
    private boolean isBot;
    
    public Player(String name,boolean isBot){
        this.name = name; 
        this.isBot=isBot;
    }

    public String getName(){
        return this.name; 
    }

    public int getScore() {
        return this.score;
    }
    
    public boolean isBot() {
    		return isBot;
    }
    
    public ArrayList<Card> PlayableCards(Card card, Color color) {
        ArrayList<Card> Phand = this.hand.getPlayerHand();
        ArrayList<Card> PlayableHand = new ArrayList<>(); 
        
        for(Card i: Phand) {
            if(i.matches(card,color)) { 
                PlayableHand.add(i);
            }
        }   
        return PlayableHand;
    }
    
    public int playableHandSize(Card card, Color color){
        return this.PlayableCards(card,color).size();
    }
    
    public boolean hasPlayableCard(Card card, Color color) {
        if(this.playableHandSize(card, color) > 0) {
            return true;  
        }
        return false;
    }
    
    public PlayerHand getHand() {
        return this.hand; 
    }
    
    public void setScore(int score) {
        this.score = score; 
    }

    public void incrementScore(int score){
        this.score += score;
    }
    
    public boolean hasWon() {
        return hand.isEmpty();
    }
}

