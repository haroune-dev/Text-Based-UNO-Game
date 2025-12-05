package deck;

import java.util.ArrayList;
// import cards.*;

import javax.smartcardio.Card;

public class DiscardPile extends Deck{

    public ArrayList<Card> getAllCards(){
        return cards;
    }

    public void clearExcept(){
        Card top = getTopCard();
        cards.clear();
        cards.add(top);
    }
}
