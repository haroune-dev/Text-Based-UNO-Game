package cards;

import enumTypes.Color;
import game.GameSession;

public abstract class WildCard extends Card {
    protected Color chosenColor=Color.BLACK;
    public void setColor(Color color) {
        this.chosenColor = color;
    }
    public Color getChosenColor() {
        return chosenColor;
    }
    @Override
    public String display(GameSession session) {
        return session.colorize(this.toString(), chosenColor);
    }
    public String displayChosenColor(GameSession session) {
        return session.colorizeChosenColor(chosenColor);
    }
}

		



