package deck;

import java.util.ArrayList;
import cards.*;

public abstract class Deck {
    protected ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public Card getTopCard(){
        return cards.get(cards.size()-1);
    }

    public Card removeTopCard (){
        return cards.remove(cards.size()-1);
    }

}
