package cards;
import enumTypes.Color;
import enumTypes.Number;
import game.GameController;
public class NumberedColoredCard extends ColoredCard {
    private Number number;

    public NumberedColoredCard(Color color, Number number) {
        super(color);
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }
    @Override
    public boolean matches(Card topCard, Color currentColor) {
        if (this.color == currentColor) {
            return true;
        }
        if (topCard instanceof NumberedColoredCard) {
            NumberedColoredCard other = (NumberedColoredCard) topCard;
            if (this.number == other.number) {
                return true;
            }
        }

       
        return false;
    }
    @Override
    public void applyEffect(GameController controller) {
    	controller.setCurrentColor(getColor());
    }

    @Override
    public String toString() {
        return color + " " + number;
    }
}
