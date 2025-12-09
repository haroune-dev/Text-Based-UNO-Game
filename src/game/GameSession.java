package game;
import java.util.Scanner;
import cards.*;
import deck.DiscardPile;
import deck.DrawPile;
import java.util.ArrayList;
import enumTypes.Color;
import player.*;

public class GameSession {

private GameController controller;
private static Scanner scanner= new Scanner(System.in);

public void run() {
	int play;
	int newRound;
	int roundNumber;
	int playersize;
	String name;
	
	play=getInput("start playing enter 1 exit enter 0",0,1);
	while(play==1) {
			roundNumber=1;
			newRound=1;
			typeWriter("========== UNO GAME ==========", 200);
			showMessage("the game modes");
			showMessage("1. Playeer VS Compuer");
			showMessage("2. Two Players");
			showMessage("3. Three Players");
			showMessage("4. Four Players");
			playersize=getInput("Chose the game mode",1,4);
			ArrayList<Player> players = new ArrayList<>(playersize);
			for (int i = 1; i <= playersize; i++) {
				showMessage("Give the name of the player number"+i);
				name=scanner.next();
				players.add(new Player(name));
			}
			while(newRound==1) {
			showMessage("Round Number"+String.valueOf(roundNumber));
			DrawPile drawPile = new DrawPile();
			DiscardPile discardPile = new DiscardPile();
			controller=new GameController(drawPile,discardPile,players);
			controller.startGame();
			while(!controller.checkWinCondition()) {
			handlePlayerTurn(controller.getCurrentPlayer());
			}
		Player winner = controller.getCurrentPlayer();
		showMessage("Congratulation"+winner.getName());
		winner.incrementScore(500);
		showMessage(winner.getName()+"Your Score now is"+String.valueOf(winner.getScore()));
		newRound=getInput("play new Round enter 1 exit enter 0",0,1);
	}
			play=getInput("Start new game with new playes enter 1 no enter0",0,1);
}
}

public void handlePlayerTurn(Player player) {
	int chosenCardIndex;
	Card chosenCard;
	Card withdrawncard;
	printGameState(player);
	if(player.hasPlayableCard(controller.getCurrentCard(), controller.getCurrentColor())) {
	chosenCardIndex=getInput("chose a card ",1,player.playableHandSize(controller.getCurrentCard(), controller.getCurrentColor()));
	chosenCard=player.PlayableCards(controller.getCurrentCard(), controller.getCurrentColor()).get(chosenCardIndex-1);
	controller.handlePlayerCard(chosenCard);
	}else {
		withdrawncard=controller.drawACard();
		printGameState(withdrawncard);
		if(controller.isValidMove(withdrawncard)) {
			controller.handlePlayerCard(withdrawncard);
		}else {
			controller.nextTurn();
		}
		
	}
}
	
public static Color askForColor() {
	int chosenCardIndex;
	Scanner scanner=new Scanner(System.in);
	System.out.println("chose a color for the wild card (Enter its index)");
	System.out.println("1. RED");
	System.out.println("2. BLUE");
	System.out.println("3. GREEN");
	System.out.println("4. YELLOW");
	do {
	chosenCardIndex=scanner.nextInt();
	}while(chosenCardIndex<1 || chosenCardIndex>4);
	switch(chosenCardIndex) {
	case 1:
		return Color.RED;
	
	case 2:
		return Color.BLUE;
	
	case 3:
		return Color.GREEN;
	
	case 4:
		return Color.YELLOW;
	}
	return Color.RED;
}
public void printGameState(Player player){ 
    System.out.print("\033[H\033[2J");
    System.out.flush();
	System.out.println("it is your turn : "+player.getName());
	try {
        Thread.sleep(700); 
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
	System.out.println("the top card is "+controller.getCurrentCard());
	try {
        Thread.sleep(2000); 
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
	System.out.println("your card are "+player.getHand().getPlayerHand());
	try {
        Thread.sleep(2000); 
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
	if(player.hasPlayableCard(controller.getCurrentCard(), controller.getCurrentColor())) {
	System.out.println("your playabale cards are "+player.PlayableCards(controller.getCurrentCard(), controller.getCurrentColor()));
	}else {
		showMessage("you dont hava any playable cards!");
		try {
	        Thread.sleep(2000); 
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
		showMessage("you hava to take a card from the draw pile");
		try {
	        Thread.sleep(2000); 
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
		showMessage("DONE");
		
	}
	}
public void printGameState(Card withdrawncard) {
		System.out.println("Your withdrawn card is "+withdrawncard);
		if(controller.isValidMove(withdrawncard)) {
			showMessage("You can play by it!");
		}else {
			showMessage("You can not play by it");
		}
	}

public int getInput(String string,int min,int max) {
	int a;
	do {
	System.out.println(string);
	a=scanner.nextInt();
	}while(a<min || a>max);
	return a;
}
public void showMessage(String string) {
	System.out.println(string);
}
public void typeWriter(String text, int delay) {
    for (char c : text.toCharArray()) {
        System.out.print(String.valueOf(c));
        try { Thread.sleep(delay); } catch (Exception e) {}
    }
    System.out.println("\n");
}




}
