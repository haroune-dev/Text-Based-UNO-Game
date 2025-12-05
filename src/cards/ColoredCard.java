package cards;

import enumTypes.Color;
public abstract class ColoredCard extends Card {
    protected Color color;

    public ColoredCard(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
}
