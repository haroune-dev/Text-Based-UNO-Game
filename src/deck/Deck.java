package deck;

import java.util.ArrayList;
// import cards.*;

// import javax.smartcardio.Card;

public abstract class Deck {
    protected ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public Card getTopCard(){
        if (cards.isEmpty()) return null;
        return cards.get(cards.size()-1);
    }

    public Card removeTopCard (){
        if (cards.isEmpty()) return null;
        return cards.remove(cards.size()-1);
    }

}
