package game;

import cards.*;
import deck.*;
import player.*;
import enumTypes.Color;
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
	this.currentPlayerIndex=1;
	this.isClockwise=true;
}
public void startGame() {
	this.drawpile.shuffle();
	for (int i = 0; i < players.size(); i++) {
		for (int j = 0; j < 7; j++) {
	    players.get(i).addCard(drawpile.drawCard());
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

public void handlePlayerCard(card playedCard) {
	playedCard.applyEffect(this);
	getCurrentPlayer().getHand().removeCard(playedCard);
	discardPile.addCard(playedCard);
	this.currentCard=playedCard;
	if(this.checkWinCondition()) {
		return;
	}else {
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
	this.currentPlayerIndex=(this.currentPlayerIndex+step)%(s+1);
	if(this.currentPlayerIndex==0 && step==1) {
		this.currentPlayerIndex=1;
	}else {
		if(this.currentPlayerIndex==0 && step==-1) {
			this.currentPlayerIndex+=s;
		}
	}
}

public boolean isValidMove(card card) {
	return card.matches(currentCard,currentColor);
}

public void skipNextPlayer() {
	int inc;
	if(isClockwise) {
		inc=1;
	}else {
		inc=-1;
	}
	int s=players.size();
	this.currentPlayerIndex=(this.currentPlayerIndex+inc)%(s+1);
	if(this.currentPlayerIndex==0 && inc==1) {
		this.currentPlayerIndex=1;
	}else {
		if(this.currentPlayerIndex==0 && inc==-1) {
			this.currentPlayerIndex+=s;
		}
	}
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
	index=(index+step)%(s+1);
	if(index==0 && step==1) {
		index=1;
	}else {
		if(index==0 && step==-1) {
			index+=s;
		}
	}
	for (int i = 0; i < n; i++) {
	this.players.get(index).getHand().addCard(drawPile.drawCard());
	}
}

public void setCurrentColor(Color color) {
	currentColor=color;
}

public void choseColor(WildCard wildCard) {
	Color chosenColor= GameSession.askForColor();
	wildCard.setColor(chosenColor);
	currentColor=chosenColor;
}

public card drawACard() {
	card withdrawncard=drawPile.drawCard();
	this.getCurrentPlayer().getHand().addCard(withdrawncard);
	return withdrawncard;
}

public card getCurrentCard() {
	return currentCard;
}

public Player getCurrentPlayer() {
	return players.get(currentPlayerIndex);
}
public boolean checkWinCondition() {
	return players.get(currentPlayerIndex).hasWon();
}
}
