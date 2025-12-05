package cards;

import enumTypes.Color;
import game.GameController;

public class WildColorCard extends WildCard{
    @Override
    public boolean matches(Card topCard, Color currentColor) {
        return true;
    }
    @Override
    public void applyEffect(GameController controller) {
        controller.chooseColorAndSet(this);
    }

    @Override
    public String toString() {
        return "WILD (" + chosenColor + ")";
    }
}
