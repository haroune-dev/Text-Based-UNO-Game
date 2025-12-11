package game;

import cards.*;
import deck.*;
import player.*;
import enumTypes.*;
import enumTypes.Number;

import java.util.ArrayList;

public class GameController {
	
private DrawPile drawPile;
private DiscardPile discardPile;
private ArrayList<Player> players;
private int currentPlayerIndex;
private Card currentCard;
private Color currentColor;
private boolean isClockwise;

public GameController(DrawPile drawpile,DiscardPile discardPile,ArrayList<Player> players) {
	this.drawPile=drawpile;
	this.discardPile=discardPile;
	this.players=players;
	this.currentPlayerIndex=0;
	this.isClockwise=true;
}
public void startGame() {
	this.creatDeck(drawPile);
	this.drawPile.shuffle();
	for (int i = 0; i < players.size(); i++) {
		for (int j = 0; j < 7; j++) {
	    players.get(i).getHand().addCard(drawPile.drawCard());
		}
}
	this.discardPile.addCard(drawPile.drawCard());
	this.currentCard=discardPile.getTopCard();
	while(currentCard instanceof WildCard) {
	this.drawPile.addCard(currentCard);
	this.drawPile.shuffle();
	this.discardPile.addCard(drawPile.drawCard());
	this.currentCard=discardPile.getTopCard();
	}
	this.currentColor=((ColoredCard) this.currentCard).getColor();
}

public void handlePlayerCard(Card playedCard) {
	getCurrentPlayer().getHand().removeCard(playedCard);
	discardPile.addCard(playedCard);
	this.currentCard=playedCard;
	if(this.checkWinCondition()) {
		return;
	}else {
		playedCard.applyEffect(this);
		this.nextTurn();
	}
}

public void nextTurn() {
	int step;
	if(isClockwise) {
		step=1;
	}else {
		step=-1;
	}
	int s=players.size();
	this.currentPlayerIndex=(this.currentPlayerIndex+step)%(s);
		if(this.currentPlayerIndex<0) {
			this.currentPlayerIndex+=s;
		}
	}

public boolean isValidMove(Card card) {
	return card.matches(currentCard,currentColor);
}

public void skipNextPlayer() {
	this.nextTurn();
}

public void reverseDirection(){
	this.isClockwise=!this.isClockwise;
}
public void drawCards(int n) {
	int index=currentPlayerIndex;
	int step;
	if(isClockwise) {
		step=1;
	}else {
		step=-1;
	}
	int s=players.size();
	index=(index+step)%(s);
		if(index<0) {
			index+=s;
		}
	for (int i = 0; i < n; i++) {
	this.players.get(index).getHand().addCard(drawPile.drawCard());
	}
}

public void setCurrentColor(Color color) {
	currentColor=color;
}

public void chooseColorAndSet(WildCard wildCard) {
	Color chosenColor;
	if(!getCurrentPlayer().isBot()) {
	chosenColor= GameSession.askForColor();
	wildCard.setColor(chosenColor);
	}else {
		chosenColor=BotChooseColor(this.getCurrentPlayer());
		wildCard.setColor(chosenColor);
	}
	currentColor=chosenColor;
}

public Card drawACard() {
	if(drawPile.isEmpty()) {
		this.refillDrawPile(drawPile);
	}
	Card withdrawncard=drawPile.drawCard();
	this.getCurrentPlayer().getHand().addCard(withdrawncard);
	return withdrawncard;
}

public int BotChooseCardIndex(Player botPlayer) {
    ArrayList<Card> playableCards = botPlayer.PlayableCards(this.currentCard, this.currentColor);
    ArrayList<Card> wildCards = new ArrayList<>();
    ArrayList<Card> coloredCards = new ArrayList<>();

    for (Card card : playableCards) {
        if (card instanceof WildCard) {
            wildCards.add(card);
        } else {
            coloredCards.add(card);
        }
    }

    if (coloredCards.isEmpty() && !wildCards.isEmpty()) {
        Card chosenWild = wildCards.get(0);
        return playableCards.indexOf(chosenWild);
    }

    if (!coloredCards.isEmpty()) {
        int bestIndex = -1;
        int highestScore = -1;

        for (int i = 0; i < coloredCards.size(); i++) {
            Card card = coloredCards.get(i);
            int score = 0;
            
            if (card instanceof ColoredCard) {
                Color cardColor = ((ColoredCard) card).getColor();
                int colorCount = 0;
                for(Card handCard : botPlayer.getHand().getPlayerHand()) {
                    if (handCard instanceof ColoredCard && ((ColoredCard)handCard).getColor() == cardColor) {
                        colorCount++;
                    }
                }
                score = colorCount;
                if (card instanceof NumberedColoredCard) {
                    Number cardNumber = ((NumberedColoredCard) card).getNumber();
                    int numberCount = 0;
                    for(Card handCard : botPlayer.getHand().getPlayerHand()) {
                        if (handCard instanceof NumberedColoredCard && ((NumberedColoredCard)handCard).getNumber() == cardNumber) {
                            numberCount++;
                        }
                    }
                    score += numberCount;
                }
            }

            if (score > highestScore) {
                highestScore = score;
                bestIndex = playableCards.indexOf(card);
            }
        }
        
        if (bestIndex != -1) {
            return bestIndex;
        }
    }

    if (!wildCards.isEmpty()) {
        Card chosenWild = wildCards.get(0);
        return playableCards.indexOf(chosenWild);
    }
    return 0;
}

public Color BotChooseColor(Player botPlayer) {
    int red = 0, blue = 0, green = 0, yellow = 0;

    for (Card c : botPlayer.getHand().getPlayerHand()) {
        if (c instanceof ColoredCard) {
            switch (((ColoredCard)c).getColor()) {
                case RED -> red++;
                case BLUE -> blue++;
                case GREEN -> green++;
                case YELLOW -> yellow++;
            }
        }
    }

    if (red >= blue && red >= green && red >= yellow) return Color.RED;
    if (blue > red && blue >= green && blue >= yellow) return Color.BLUE;
    if (green > red && green > blue && green >= yellow) return Color.GREEN;
    return Color.YELLOW;
}
public Card getCurrentCard() {
	return currentCard;
}

public Color getCurrentColor() {
	return currentColor;
}

public Player getCurrentPlayer() {
	return players.get(currentPlayerIndex);
}
public boolean checkWinCondition() {
	return players.get(currentPlayerIndex).hasWon();
}

public void refillDrawPile(DrawPile drawPile) {
	drawPile.refillFrom(discardPile);
}

public void creatDeck(DrawPile drawPile) {
	
	Color[] allColor = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
	Number[] allNumber = Number.values();
	Color color;
	Number number;
	
	for (int i = 0; i < 4; i++) {
	 color=allColor[i];
	 for (int j = 0; j < 10; j++) {
		 number=allNumber[j];
		if(number==Number.ZERO) {
			drawPile.addCard( new NumberedColoredCard(color,number));
		}else {
			drawPile.addCard( new NumberedColoredCard(color,number));
			drawPile.addCard( new NumberedColoredCard(color,number));
		}
	}
	}
	for (int i = 0; i <4 ; i++) {
		color=allColor[i];
		for (int j = 0; j < 2; j++) {
			drawPile.addCard(new DrawTwoCard(color));
			drawPile.addCard(new ReverseCard(color));
			drawPile.addCard(new SkipCard(color));
		}
	}
	for (int i = 0; i < 4; i++) {
		drawPile.addCard(new WildColorCard());
		drawPile.addCard(new WildColorDrawFourCard());
		
	}
}
}
