package cards;

import enumTypes.Color;
import game.GameSession;
public class ColoredCard extends Card {
    protected Color color;

    public ColoredCard(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    @Override
    public String display(GameSession session) {
        return session.colorize(this.toString(), color);
    }
}

