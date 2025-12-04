package cards;

import enumTypes.Color;
import game.GameController;
public class WildColorDrawFourCard implements Actionable extends WildCard {
    @Override
    public boolean matches(Card topCard, Color currentColor) {
        return true;
    }
    @Override
    public void applyEffect(GameController controller) {
        controller.chooseColor(this);
        controller.drawCards(4);
    }

    @Override
    public String toString() {
        return "WILD DRAW FOUR (" + chosenColor + ")";
    }
}
