# Dice Golf

A Java Golf game where the hole is a defined number of spaces away and you roll dice in order determine the distance you hit the ball.

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





