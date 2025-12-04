package cards;
import enumTypes.Color;
import game.GameController;
interface Actionable{
	public void applyEffect(GameController controller);
}
public abstract class Card {
	public abstract boolean matches(Card topCard, Color currentColor);
    @Override
    public abstract String toString();
}

