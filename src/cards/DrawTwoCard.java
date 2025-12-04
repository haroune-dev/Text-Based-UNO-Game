package cards;

import enumTypes.Color;
import game.GameController;
public class DrawTwoCard extends ColoredCard implements Actionable  {

    public DrawTwoCard(Color color) {
        super(color);
    }
    @Override
    public boolean matches(Card topCard, Color currentColor) {
        if (this.color == currentColor) {
            return true;
        }
        if (topCard instanceof DrawTwoCard) {
            return true;
        }

        return false;
    }
    @Override
    public void applyEffect(GameController controller) {
        controller.drawCards(2);
    }

    @Override
    public String toString() {
        return color + " DRAW TWO";
    }
}
