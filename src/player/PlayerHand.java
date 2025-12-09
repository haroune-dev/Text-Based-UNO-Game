package player;

import java.util.ArrayList;
import cards.*;


public class PlayerHand {
    
    private ArrayList<Card> List = new ArrayList<>(); 
    
    public void addCard(Card X) {
        List.add(X);  
    }

    public void removeCard(Card X) {
        List.remove(X);  
    }

    public int handsize() {
        return List.size(); 
    }

    public boolean isEmpty() {
        return List.isEmpty();  
    }

    public ArrayList<Card> getPlayerHand(){
        return this.List;  
    }
}

		             