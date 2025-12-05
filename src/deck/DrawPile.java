package deck;

import java.util.Collections;
import java.util.ArrayList;
// import cards.*;

public class DrawPile extends Deck {

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card drawCard(){
        return removeTopCard();
    }

            
    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public void refillFrom(DiscardPile discardPile){
        
        Card top = discardPile.getTopCard();

        ArrayList<Card> cards1 = new ArrayList<>(discardPile.getAllCards());
        cards1.remove(top); 

        discardPile.clearExcept();

        for(Card c : cards1){
            this.addCard(c);
        }
        this.shuffle();
    }

}
