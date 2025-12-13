package game;
import java.util.Scanner;
import cards.*;
import deck.DiscardPile;
import deck.DrawPile;
import java.util.ArrayList;
import java.util.Comparator;

import enumTypes.Color;
import player.*;

public class GameSession {
	
public static final String RESET = "\u001B[0m";

public static final String BOLD = "\u001B[1m";
public static final String UNDERLINE = "\u001B[4m";

public static final String BLACK   = "\u001B[30m";
public static final String RED     = "\u001B[31m";
public static final String GREEN   = "\u001B[32m";
public static final String YELLOW  = "\u001B[33m";
public static final String BLUE    = "\u001B[34m";
public static final String WHITE   = "\u001B[37m";

public static final String BRIGHT_CYAN    = "\u001B[96m";
public static final String BRIGHT_YELLOW  = "\u001B[93m";
public static final String BRIGHT_WHITE   = "\u001B[97m";

public static final String BG_BLACK   = "\u001B[40m";
public static final String BG_RED     = "\u001B[41m";
public static final String BG_GREEN   = "\u001B[42m";
public static final String BG_YELLOW  = "\u001B[43m";
public static final String BG_BLUE    = "\u001B[44m";
public static final String BG_MAGENTA = "\u001B[45m";
public static final String BG_CYAN    = "\u001B[46m";
public static final String BG_WHITE   = "\u001B[47m";

public static final String BG_BRIGHT_BLACK   = "\u001B[100m";
public static final String BG_BRIGHT_RED     = "\u001B[101m";
public static final String BG_BRIGHT_GREEN   = "\u001B[102m";
public static final String BG_BRIGHT_YELLOW  = "\u001B[103m";
public static final String BG_BRIGHT_BLUE    = "\u001B[104m";
public static final String BG_BRIGHT_MAGENTA = "\u001B[105m";
public static final String BG_BRIGHT_CYAN    = "\u001B[106m";
public static final String BG_BRIGHT_WHITE   = "\u001B[107m";



private GameController controller;
private static Scanner scanner= new Scanner(System.in);

public void run() {
	int play;
	int newRound;
	int roundNumber;
	int playersize;
	String name;
	
	play=getInput(BOLD+"start playing? "+GREEN+ "[1] yse "+RESET+ RED +"[0] exit "+RESET,0,1);
	while(play==1) {
			roundNumber=0;
			newRound=1;
			typeWriter("========== UNO GAME ==========", 200);
			showMessage(BOLD+UNDERLINE+"the game modes : "+RESET);
			showMessage("");
			showMessage("1. Playeer VS BOT");
			showMessage("2. Two Players");
			showMessage("3. Three Players");
			showMessage("4. Four Players");
			showMessage("");
			playersize=getInput(BOLD+">> Chose the game mode (1-4)",1,4);
			ArrayList<Player> players = new ArrayList<>(playersize);
			if (playersize == 1) {
			    showMessage(">> Give the name of the"+BOLD+" player");
			    name=scanner.next();
			    players.add(new Player(name, false)); 
			    players.add(new Player("BOT", true)); 
			} else {
			for (int i = 1; i <= playersize; i++) {
				showMessage(">> Give the name of the"+BOLD+" player number "+i);
				name=scanner.next();
				players.add(new Player(name,false));
			}
			}
			while(newRound==1) {
			roundNumber++;
			printRound(roundNumber);
			DrawPile drawPile = new DrawPile();
			DiscardPile discardPile = new DiscardPile();
			controller=new GameController(drawPile,discardPile,players);
			controller.startGame();
			scanner.nextLine(); 
			while(!controller.checkWinCondition()) {
			if(!controller.getCurrentPlayer().isBot()) {
			handlePlayerTurn(controller.getCurrentPlayer());
			}else {
				handleBotTurn(controller.getCurrentPlayer());
			}
			}
		Player winner = controller.getCurrentPlayer();
		showMessage(YELLOW+" 🏆 "+RESET+BOLD+" Congratulation " +BG_BLACK +BRIGHT_YELLOW +winner.getName() +RESET+BOLD+" You won this Round");
		winner.incrementScore(500);
		showMessage(BOLD + winner.getName() + " Score : "+BG_BLACK + BRIGHT_YELLOW + winner.getScore() + RESET);
		printLeaderboard(players);
		newRound=getInput("play a new Round"+GREEN+ "[1] yse "+RESET+ RED +"[0] exit "+RESET,0,1);
	}
			play=getInput(BOLD+"Start new game with new playes enter"+RESET +GREEN+ "[1] yse "+RESET+ RED +"[0] exit "+RESET,0,1);
			clearScreen();
}
	    showMessage(BOLD+" Thanks for playing UNO! "+RESET);
	    typeWriter("GAME EXITING ...",300 );
	    clearScreen();
	    System.exit(0);
}

public void handlePlayerTurn(Player player) {
	int chosenCardIndex;
	int max;
	Card chosenCard;
	Card withdrawncard;
	
	max=player.playableHandSize(controller.getCurrentCard(), controller.getCurrentColor());
	printGameState(player);
	if(player.hasPlayableCard(controller.getCurrentCard(), controller.getCurrentColor())) {
	chosenCardIndex=getInput(BOLD+"chose a playabe card (1-"+max+")",1,max);
	chosenCard=player.PlayableCards(controller.getCurrentCard(), controller.getCurrentColor()).get(chosenCardIndex-1);
	controller.handlePlayerCard(chosenCard);
	waitToFinishPlayerTurn(player.isBot());
	}else {
		withdrawncard=controller.drawACard();
		printGameState(withdrawncard);
		if(controller.isValidMove(withdrawncard)) {
			controller.handlePlayerCard(withdrawncard);
			waitToFinishPlayerTurn(player.isBot());
		}else {
			waitToFinishPlayerTurn(player.isBot());
			controller.nextTurn();
		}
		
	}
}

public void printGameState(Player player){
	clearScreen();
	showMessage(BOLD+BLACK+"==============================================");
	showMessage(BG_BLACK + WHITE + BOLD +"it is your turn : "+BRIGHT_YELLOW +BOLD+player.getName()+""+RESET);
	sleep(800);
	showMessage("-----------------------------------");
	waitForNextPlayer(player.isBot());
	showMessage("-----------------------------------");
	sleep(800);
	showMessage(BOLD+"the top card is : "+controller.getCurrentCard().display(this)+RESET);
	showMessage("-----------------------------------");
	sleep(800);
	printCards(player.getHand().getPlayerHand(),0);
	showMessage("-----------------------------------");
	sleep(800);
	if(player.hasPlayableCard(controller.getCurrentCard(), controller.getCurrentColor())) {
		printCards(player.PlayableCards(controller.getCurrentCard(), controller.getCurrentColor()),1);
		System.out.println("-----------------------------------");
	}else {
		showMessage(RED+BOLD+"you dont hava any playable cards! You must draw..."+RESET);
		sleep(900);
		showMessage(BOLD+"DONE"+RESET);
		
	}
	}

public void printGameState(Card withdrawncard) {
	System.out.println("Your withdrawn card is : "+withdrawncard.display(this));
	sleep(1000);
	if(controller.isValidMove(withdrawncard)) {
		showMessage(GREEN + BOLD+"✅ matche "+RESET+GREEN+" You can play by it!"+RESET);
	}else {
		showMessage(RED + BOLD +"❌ not matche "+RESET+RED+" You can not play by it"+RESET);
	}
	sleep(1000);
}

public static Color askForColor() {
	int chosenCardIndex;
	showMessage("chose a color for the wild card (1-4)");
	showMessage("[1]."+RED+" RED"+RESET);
	showMessage("[2]. "+BLUE+"BLUE"+RESET);
	showMessage("[3]. "+GREEN+"GREEN"+RESET);
	showMessage("[4]. "+YELLOW+"YELLOW"+RESET);
	chosenCardIndex=getInput(">>",1,4);
	switch(chosenCardIndex) {
	case 1:
		return Color.RED;
	
	case 2:
		return Color.BLUE;
	
	case 3:
		return Color.GREEN;
	
	case 4:
		return Color.YELLOW;
	default:
		return Color.RED;
	}
}

public void handleBotTurn(Player player) {
    int chosenCardIndex;
    Card chosenCard;
    Card withdrawnCard;
    		printBotGameState(player);
    			if(player.hasPlayableCard(controller.getCurrentCard(), controller.getCurrentColor())) {
        chosenCardIndex = controller.BotChooseCardIndex(player);
        chosenCard=player.PlayableCards(controller.getCurrentCard(), controller.getCurrentColor()).get(chosenCardIndex);
        controller.handlePlayerCard(chosenCard);
        printBotPlayedCard(chosenCard);
        waitToFinishPlayerTurn(player.isBot());
    } else {
        withdrawnCard = controller.drawACard();
        printBotGameState(withdrawnCard);
        if (controller.isValidMove(withdrawnCard)) {
        	    controller.handlePlayerCard(withdrawnCard);
        	    printBotwithdrawnCard(withdrawnCard);
        		waitToFinishPlayerTurn(player.isBot());
        } else {
        		waitToFinishPlayerTurn(player.isBot());
            controller.nextTurn();
        }
    }
}

public void printBotGameState(Player player) {
		clearScreen();
		showMessage(BOLD+BLACK+"==============================================");
		showMessage(BG_BLACK + WHITE + BOLD +"it is your turn : "+BRIGHT_YELLOW +BOLD+player.getName()+""+RESET);
		showMessage("-----------------------------------");
		sleep(800);
		waitForNextPlayer(player.isBot());
		showMessage("-----------------------------------");
		showMessage(BOLD+"the top card is : "+controller.getCurrentCard().display(this)+RESET);
		showMessage("-----------------------------------");
		sleep(1000);
		if(!player.hasPlayableCard(controller.getCurrentCard(), controller.getCurrentColor())){
			showMessage(RED+BOLD+"The Bot does not have any playable cards! The Bot must draw..."+RESET);
			sleep(1000);
			showMessage(BOLD+"DONE"+RESET);
			
		}
	}

public void printBotGameState(Card withdrawncard) {
	sleep(1000);
	if(controller.isValidMove(withdrawncard)) {
		showMessage(GREEN + BOLD+"✅ matche "+RESET+GREEN+"The Bot can play by it!"+RESET);
		showMessage("-----------------------------------");
	}else {
		showMessage(RED + BOLD +"❌ not matche "+RESET+RED+" the bot can not play by it"+RESET);
		showMessage("-----------------------------------");
}
sleep(1000);
}

public void printBotPlayedCard(Card playedCard) {
	showMessage(BOLD+" The Bot play : "+playedCard.display(this));
	showMessage("-----------------------------------");
	if(playedCard instanceof WildCard) {
		sleep(800);
		showMessage(BOLD+" the Bot chose the color "+((WildCard)playedCard).displayChosenColor(this));
		showMessage("-----------------------------------");
	}
}

public void printBotwithdrawnCard(Card withdrawnCard) {
	if(controller.isValidMove(withdrawnCard)){
	showMessage(BOLD+"BOT plays the drawn card: : "+withdrawnCard.display(this)+RESET);
	showMessage("-----------------------------------");
	if(withdrawnCard instanceof WildCard) {
		sleep(800);
		showMessage(BOLD+" the Bot chose the color "+((WildCard)withdrawnCard).displayChosenColor(this)+RESET);
		showMessage("-----------------------------------");
	}
}
}

public void waitForNextPlayer(boolean isBot) {
    if(!isBot) {
    showMessage(BOLD +">>press ENTER to start your turn." + RESET);
    }else {
    	showMessage(BOLD +">>press ENTER to start Bot turn." + RESET);
    }
    scanner.nextLine(); 
}

public void waitToFinishPlayerTurn(boolean isBot) {
    if(!isBot) {
    showMessage(BOLD +">>press ENTER to finish your turn." + RESET);
    }else {
    	showMessage(BOLD +">>press ENTER to finish Bot turn." + RESET);
    }
    scanner.nextLine(); 
}



public void printCards(ArrayList<Card> hand,int x) {
	if(x==0) {
	showMessage(BOLD + "Your cards:" + RESET);
	}else {
		showMessage(BOLD + "Your plyable cards :" + RESET);
	}

    for (int i = 0; i < hand.size(); i++) {
        Card card = hand.get(i);

        String index = "[" + (i + 1) + "] ";

        System.out.print(index + card.display(this) + "   ");
    }
    System.out.println();
}

public void printRound(int round) {
    String border = "=".repeat(30);
    showMessage(RED + border + RESET);
    showMessage(BG_RED + WHITE + BOLD + "      ROUND " + round + " START!      " + RESET);
    showMessage(RED + border + RESET);
    sleep(1000);
}

public static int getInput(String string,int min,int max) {
	int input;
	int cpt=1;
	do {
	if(cpt==1) {
	showMessage(string);
	}else {
		showMessage(BOLD+RED+"! please enter an integer betwen "+min+" and "+max+""+RESET);
	}
	while(!scanner.hasNextInt()) {
		showMessage(BOLD+RED+"! please enter an integer"+RESET);
		scanner.nextLine();
	}
	input=scanner.nextInt();
	scanner.nextLine();
	cpt++;
	}while(input<min || input>max);
	return input;
}

public static void showMessage(Object obj) {
	System.out.println(obj);
}
public void typeWriter(String text, int delay) {
    for (char c : text.toCharArray()) {
        System.out.print(String.valueOf(c));
        try { Thread.sleep(delay); } catch (Exception e) {}
    }
    System.out.println("\n");
}


public  String colorize(String text, Color color) {
    switch (color) {
        case RED: return RED + text + RESET;
        case BLUE: return BLUE + text + RESET;
        case GREEN: return GREEN + text + RESET;
        case YELLOW: return YELLOW + text + RESET;
        default: return text;
    }
    
}

public String colorizeChosenColor(Color ChosenColor) {
    switch (ChosenColor) {
    case RED: return RED + ChosenColor.toString() + RESET;
    case BLUE: return BLUE + ChosenColor.toString() + RESET;
    case GREEN: return GREEN + ChosenColor.toString() + RESET;
    case YELLOW: return YELLOW + ChosenColor.toString() + RESET;
    default: return ChosenColor.toString();
}
}

public void printLeaderboard(ArrayList<Player> players) {
	String rank;
	ArrayList<Player> rankedPlayers = new ArrayList<>(players);
	rankedPlayers.sort(Comparator.comparingInt(Player::getScore).reversed());
	showMessage(BOLD +"\n======"+RESET+BOLD+ BG_BLACK +BRIGHT_YELLOW +" 🏆 LEADERBOARD 🏆 "+RESET+BOLD+"======\n" + RESET);
	for (int i = 0; i < rankedPlayers.size(); i++) {
		switch(i) {
			case 0:
				rank="First";
				break;
			case 1:
				rank="Second";
				break;
			case 2:
				rank="third";
				break;
			case 3:
				rank="fourth";
				break;
			default:
				rank="first";
		}
		showMessage(rank+"  :  " +rankedPlayers.get(i).getName()+"    |   "+rankedPlayers.get(i).getScore());
		}
		showMessage(BOLD+"===============================\n" + RESET);
}


public void sleep(int delay) {
    try {
        Thread.sleep(delay);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

public void clearScreen() {
		showMessage("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
}

}
