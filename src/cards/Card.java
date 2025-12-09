package cards;
import enumTypes.Color;
import game.GameController;
import game.GameSession;

public abstract class Card {
	public abstract boolean matches(Card topCard, Color currentColor);
	public abstract void applyEffect(GameController controller);
    @Override
    public abstract String toString();
    public abstract String display(GameSession session);
}

