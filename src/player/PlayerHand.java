package player;

import java.util.ArrayList;

public class PlayerHand {
    // attribute
    private ArrayList<Card> List = new ArrayList();  // List of cards in the hand

    // method
    // All checks are performed on the main page

    public void addCard(Card X) {
        List.add(X);  // Add a card to the hand
    }

    public void removeCard(Card X) {
        List.remove(X);  // Remove a card from the hand
    }

    public int handsize() {
        return List.size();  // Return number of cards in hand
    }

    public boolean isEmpty() {
        return List.isEmpty();  // Check if hand is empty
    }

    public ArrayList<Card> getPlayerHand(){
        return this.List;  // Return list of cards
    }
}

		             