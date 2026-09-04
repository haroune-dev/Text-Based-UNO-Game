<div align="center">

# 🎴 UNO Console Game

**A console-based implementation of the classic UNO card game written in Java.**

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Platform](https://img.shields.io/badge/Platform-Console%20%2F%20CLI-grey?style=for-the-badge&logo=windowsterminal&logoColor=white)](/)
[![Status](https://img.shields.io/badge/Status-Complete-brightgreen?style=for-the-badge)](/)

*Play against a bot or with up to four players in the terminal.*

[Getting Started](#-getting-started) •
[Features](#-features) •
[Screenshots](#-screenshots) •
[Architecture](#-architecture) •

</div>

---

##  About

This project is a console-based implementation of the classic UNO card game developed in Java using object-oriented programming principles. It supports both single-player mode against a bot and local multiplayer for up to four players while following the standard UNO rules.

The project was built as an educational OOP project with a focus on clean code organization, inheritance, abstraction, encapsulation, and polymorphism.

---

##  Features

| Feature | Description |
|---|---|
| 🤖 **Smart Bot AI** | Play against an intelligent bot that strategically picks cards based on color frequency and card type |
| 👥 **Multiplayer** | Support for 2, 3, or 4 human players on the same terminal |
| 🎨 **Colored Output** | ANSI color-coded cards ( Red,  Blue,  Green,  Yellow) for an immersive experience |
| 🔄 **Full UNO Ruleset** | Skip, Reverse, Draw Two, Wild, and Wild Draw Four — all action cards implemented |
| 🏆 **Leaderboard & Scoring** | Track scores across multiple rounds with a ranked leaderboard |
| ⚡ **UNO Call Mechanic** | Forget to type "UNO" when you're down to one card? That's a 2-card penalty! |
| 🎬 **Typewriter Animations** | Smooth text animations and timed reveals for a polished feel |
| 🔁 **Multi-Round Support** | Play multiple rounds in a single session with persistent scoring |

---

##  Screenshots

<div align="center">

###  Game Menu & Mode Selection
<img src="screenshots/Capture d_écran 2026-03-28 150343.png" alt="Game Menu" width="500"/>

> *Choose between Player vs Bot, 2 Players, 3 Players, or 4 Players mode.*

---

###  Turn Start Screen
<img src="screenshots/Capture d_écran 2026-03-28 150422.png" alt="Turn Start" width="500"/>

> *Clean, focused UI for each player's turn with privacy protection in multiplayer.*


---

###  Gameplay — Player Turn
<img src="screenshots/Capture d_écran 2026-03-28 150550.png" alt="Gameplay" width="700"/>

> *View your hand, playable cards, and choose your next move. Cards are color-coded for easy identification.*

</div>

---


##  Getting Started

### Prerequisites

- **Java 17** or higher — [Download here](https://www.oracle.com/java/technologies/downloads/)
- A terminal that supports **ANSI color codes** (Windows Terminal, Git Bash, macOS Terminal, Linux Terminal)

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/haroune-dev/UNO-Console-Game.git

# 2. Navigate to the project directory
cd UNO-Console-Game

# 3. Compile the source files (Linux/macOS or Git Bash)
javac -d bin src/**/*.java

# On Windows CMD, use explicit paths:
javac -d bin src\cards\*.java src\deck\*.java src\player\*.java src\enumTypes\*.java src\game\*.java


# 4. Run the game
java -cp bin game.Drive
```

### Quick Start with Eclipse

1. **Import** the project into Eclipse (`File → Import → Existing Projects`)
2. **Run** `Drive.java` as a Java Application

---

##  Architecture

###  Project Structure

```
UNO-Console-Game/
├── src/
│   ├── cards/                    # Card hierarchy (Abstract → Concrete)
│   │   ├── Card.java             # Abstract base class
│   │   ├── ColoredCard.java      # Abstract colored card
│   │   ├── NumberedColoredCard.java
│   │   ├── DrawTwoCard.java
│   │   ├── ReverseCard.java
│   │   ├── SkipCard.java
│   │   ├── WildCard.java         # Abstract wild card
│   │   ├── WildColorCard.java
│   │   └── WildColorDrawFourCard.java
│   │
│   ├── deck/                     # Deck management
│   │   ├── Deck.java             # Abstract deck
│   │   ├── DrawPile.java         # Draw pile with shuffle & refill
│   │   └── DiscardPile.java      # Discard pile
│   │
│   ├── player/                   # Player management
│   │   ├── Player.java           # Player entity (human & bot)
│   │   └── PlayerHand.java       # Hand management
│   │
│   ├── enumTypes/                # Game enumerations
│   │   ├── Color.java            # RED, BLUE, GREEN, YELLOW
│   │   └── Number.java           # ZERO through NINE
│   │
│   └── game/                     # Game engine
│       ├── Drive.java            # Entry point (main)
│       ├── GameController.java   # Core game logic & rules
│       └── GameSession.java      # UI, I/O, and session management
│
├── screenshots/                  # Project screenshots
└── README.md
```

###  Class Diagram

<div align="center">
<img src="screenshots/Blank diagram.png" alt="UML Class Diagram" width="800"/>

> *UML Class Diagram showing the complete inheritance hierarchy and relationships.*
</div>

###  Design Highlights

- **Abstract Card Hierarchy** — `Card` → `ColoredCard` / `WildCard` → Concrete cards. Each card type implements its own `matches()` and `applyEffect()` logic via polymorphism.
- **Separation of Concerns** — `GameController` handles rules and state, `GameSession` manages UI and player interaction, and `Drive` serves as the entry point.
- **Bot Strategy Pattern** — The bot selects cards based on a scoring algorithm that evaluates color frequency and card type in its hand.
- **Standard 108-Card Deck** — Faithfully implements the official UNO deck composition.

---

##  How to Play

1. Select a game mode.
2. Enter the player names.
3. Play a valid card or draw one from the deck.
4. Action cards apply their effects immediately.
5. If you have one card left, type `uno` before ending your turn.
6. The first player to empty their hand wins the round.

---

##  Tech Stack

| Technology | Purpose |
|---|---|
| **Java 17+** | Core language with modern switch expressions |
| **OOP** | Inheritance, polymorphism, encapsulation, abstraction |
| **ANSI Escape Codes** | Terminal colors and text styling |
| **Collections Framework** | `ArrayList` for dynamic hand & deck management |
| **Scanner** | Terminal input handling |

---

##  Authors

- **Haroune-dev**
- **ayoubgz1**
- **0xraouf Abderraouf**
- **AbdelhadiZA**

---
