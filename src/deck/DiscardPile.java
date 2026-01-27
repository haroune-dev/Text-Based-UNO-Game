package deck;

import cards.*;
import java.util.ArrayList;

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
