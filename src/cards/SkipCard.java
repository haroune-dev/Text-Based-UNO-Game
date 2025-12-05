package cards;

import enumTypes.Color;
import game.GameController;
public class SkipCard extends ColoredCard{

    public SkipCard(Color color) {
        super(color);
    }
    @Override
    public boolean matches(Card topCard, Color currentColor) {        
        if (this.color == currentColor) {
            return true;
        }
        if (topCard instanceof SkipCard) {
            return true;
        }
        return false;
    }
    @Override
    public void applyEffect(GameController controller) {
        controller.skipNextPlayer();
        controller.setCurrentColor(getColor());
    }

    @Override
    public String toString() {
        return color + " SKIP";
    }
}

