package cards;

import enumTypes.Color;
import game.GameController;
public class ReverseCard extends ColoredCard {

    public ReverseCard(Color color) {
        super(color);
    }
    @Override
    public boolean matches(Card topCard, Color currentColor) {
        if (this.color == currentColor) {
            return true;
        }
        if (topCard instanceof ReverseCard) {
            return true;
        }

        return false;
    }
    @Override
    public void applyEffect(GameController controller) {
        controller.reverseDirection();
        controller.setCurrentColor(getColor());
    }

    @Override
    public String toString() {
        return color + " REVERSE";
    }
}


