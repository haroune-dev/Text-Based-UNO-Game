package game;

import cards.*;
import deck.*;
import player.*;
import enumTypes.*;
import enumTypes.Number;

import java.util.ArrayList;

public class GameController {
	
private DrawPile drawpile;
private DiscardPile discardPile;
private ArrayList<Player> players;
private int currentPlayerIndex;
private Card currentCard;
private Color currentColor;
private boolean isClockwise;

public GameController(DrawPile drawpile,DiscardPile discardPile,ArrayList<Player> players) {
	this.drawpile=drawpile;
	this.discardPile=discardPile;
	this.players=players;
	this.currentPlayerIndex=0;
	this.isClockwise=true;
}
public void startGame() {
	this.creatDeck(drawpile);
	this.drawpile.shuffle();
	for (int i = 0; i < players.size(); i++) {
		for (int j = 0; j < 7; j++) {
	    players.get(i).getHand().addCard(drawpile.drawCard());
		}
}
	this.discardPile.addCard(drawpile.drawCard());
	this.currentCard=discardPile.getTopCard();
	
	while(currentCard instanceof WildCard) {
	this.drawpile.addCard(currentCard);
	this.drawpile.shuffle();
	this.discardPile.addCard(drawpile.drawCard());
	this.currentCard=discardPile.getTopCard();
	}
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
	this.players.get(index).getHand().addCard(drawpile.drawCard());
	}
}

public void setCurrentColor(Color color) {
	currentColor=color;
}

public void chooseColorAndSet(WildCard wildCard) {
	Color chosenColor= GameSession.askForColor();
	wildCard.setColor(chosenColor);
	currentColor=chosenColor;
}

public Card drawACard() {
	Card withdrawncard=drawpile.drawCard();
	this.getCurrentPlayer().getHand().addCard(withdrawncard);
	return withdrawncard;
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














