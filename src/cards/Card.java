package cards;
import enumTypes.Color;
import game.GameController;

public abstract class Card {
	public abstract boolean matches(Card topCard, Color currentColor);
	public abstract void applyEffect(GameController controller);
    @Override
    public abstract String toString();
}

