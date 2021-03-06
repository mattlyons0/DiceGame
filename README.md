# Dice Golf

A Java Golf game where the hole is a defined number of spaces away and you roll dice in order determine the distance you hit the ball.

![Screenshot](Screenshot.png)

[Trello](https://trello.com/b/rMvKnNd1/dicegame-project)

## Definitions
* 1-4 Players play on the same computer
* Players take turns based on who is furthest from the hole
* Players play a number of holes, the player with the lowest number of overall turns wins
* Wins and Losses will be recorded
* Number of Turns for each player will be recorded

## Gameplay
* Start Hole
* Roll Dice for Multiplier *n* on how many dice will be rolled for move
* Roll *n* dice, add them up to calculate your move. *n* * 6(sides) can never be greater than the distance to the hole (for now)
* For subsequent moves the *n* dice will be shrunk and the number on the dice will be shrunk so you can never shoot over the hole (for now)
* Once the ball is in the hole, the turns taken (rolls) will be recorded as score.

## How To Play
* Compile like any java file into a jar, or [Download a Jar (Final Version)](http://mattlyons.net/downloads/school/cse360/DiceGameFinal.jar)
* Run Jar
* Assuming this is the first time running at least one player must be created by clicking the 'Create New Player' button. Otherwise players from last session will be loaded upon startup.
* Hit the 'Start Game' button (modify the holes before clicking if desired)
* Roll the dice until you make it to the hole.
* Try to use the least strokes possible.
* Upon finishing a hole the game will advance to the next hole
* Upon finishing an entire match the game will show the statistics screen
* The current game's statistics will be stored in a local directory until another game is started.


## Usernames
* Matt Lyons: mattlyons0
* David Lukacs: Dlukacs
* David McClure: Jambo-Bwana
* Daniel Kercheski: kerchedj 
