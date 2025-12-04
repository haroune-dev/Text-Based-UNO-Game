package cards;

import enumTypes.Color;

public abstract class WildCard extends Card {
    protected Color chosenColor;
    public void setColor(Color color) {
        this.chosenColor = color;
    }
    public Color getChosenColor() {
        return chosenColor;
    }
}

		

