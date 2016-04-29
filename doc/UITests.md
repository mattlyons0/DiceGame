### The following are Tests for the UI that the QA team would do to ensure the UI elements are working as expected:

#### Overall

- Upon launching the application a window should pop up
- This window should be sized appropriately (excluding HI-DPI devices, swing cannot change DPI resolution) as to not cut off or shrink any elements on the screen
- Clicking the Close button on the window should close the java application and window

#### Main Menu

- There should be a Start Game button
- The Start Game button should be initially disabled (if no players are created) with a tooltip stating a player must be created to play.
- There should be a input field with the value 3 in it next to Start Game. It should have the label 'Holes' next to it.
- The Start Game button should only become active under the following conditions: There is at least 1 player created & The holes field contains a number > 0.
- If one or both of those conditions is not met the Start Game button should be disabled with a tooltip containing one of the missing conditions.
- If the value in the holes field is not a Integer the value should turn red.
- Clicking the Start Game button should maximize the window
- Clicking the Start Game button should switch to a different view (see Game Screen)
- There should be a Create New Player button
- Clicking Create New Player button should switch to a different view (see Player Creation)
- There should be a Statistics button
- The statistics button should switch to a different view (see Statistics)
- There should be a Quit Game button
- Clicking the Quit Game button should close the window and the java application
- Upon the bottom of the of the screen there should be a label stating either no players are created or listing the created players
- Upon creating a new player, this player's name should be added to the list of players at the bottom of the Main Menu screen
- There should be a comma in between every player's name if there are more than one

#### Game Screen

- Clicking the Start Game button should show 3 layout elements
- There should be a section at the top of the window containing a visualization of the game (See Game Animation)
- The Animation should take up approximately a third of the screen vertically and fill horizontally.
- There should be a Table in the middle of the screen vertically (See Game Table)
- The table should take up approximately a third of the screen vertically and fill horizontally.
- There should be a section at the bottom of the window containing a inset border and game controls (See Game Controls)
- Thee Game Controls should be taking up approximately one third of the screen vertically and fill horizontally

##### Game Animation

- At the top left corner there should be a label in white text containing the hole number. This number should increment upon advancing to the next hole.
- In the center of the screen there should be a Golf Green. This should contain a set of balls at the left.
- Each ball should be colored randomly and match the color of the player's Row in the Game Table.
- The balls may initially overlap one another.
- There should be a ball for every player.
- There should be a hole. This hole will be in a randomized location vertically while still remaining on the green.
- The hole will be closer or further in the green depending on how far away the hole is.
- In each hole played the drawn hole on the course should be in a slightly different location depending on the hole's length and the random variation of height with the hole.
- Each Player's ball should represent the distance the player is away from the hole.
- Each time a player hits Roll in the Game Controls, the ball should move closer to the hole according to the dice roll they got.
- Movement should not be fully animated, but the balls should 'jump' forwards.
- Any ball should never go past the hole.
- Upon advancing to a new hole the ball locations should reset to the start (left side) of the green.

##### Game Table
- The table should contain 3 headers, Name, Turns and Distance from Hole
- Each row's text should be colored with the same color as that player's ball in the animation.
- Each row's background color should be either white or black in order to ensure readability of the text.
- For the column Name: Each player's name should appear as a row.
- For the column Strokes: Each player's strokes should be counted upwards after the first roll of their turn.
- For the column Distance from Hole: Each player's distance from hole should update with respect to the distance they are away from the hole after each roll.
- All of this data should be updated every time a player hits roll.

##### Game Controls
- The bottom should contain the controls for the game (Roll Dice Multiplier or Dice Roll buttons)
- There should be a label at the top of stating which player's turn it is. This label should switch in accordance to the logic after each player's turn.
- When Roll Dice Multiplier is clicked 1-6 dice buttons should appear with the label 'Roll'
- Upon clicking any of these buttons, in any given order, the Distance From Hole should be subtracted by the amount displayed on the die
- The amount displayed should be a picture of a die with the given roll on it. This roll should be a value of 0-6 inclusive. A zero roll is seen is a missed swing (and visualized with a blank die).
- Upon clicking the first die (in any order) the strokes should increment by one.
- After all the die have been clicked as long as the Distance from Hole of all players is not 0 a Next Turn button should appear.
- Pressing the Next Turn button should show the 'Roll Dice Multiplier' button once again and this loop will continue until the Distance from Hole is 0 for all players.
- If the distance from hole of all players is 0, either a Next Hole button should appear or a End Game button.
- If the current hole is the last hole (selected in the Main Menu before pressing start), End Game should appear and the Statistics View should be displayed upon clicking.
- If the current hole is not the last hole a Next Hole button should appear, incrementing the hole number in the Game Animation, Resetting the Values in the Game Table and Resetting the Player's Turn Label.

#### Player Creation

- Clicking the Create New Player button should bring a switch window to: 2 buttons, 2 labels, and 1 field for text. 
- The window layout should default size, the same as Start Menu screen. 
- "Back" button should be located on the top left corner of the window.
- Clicking the "Back" button should return the user back to the start menu. 
- "Add" button should be located in the center to the right. 
- Clicking the "Add" button should change the display label beneath it to say, "A name must be entered".
- Clicking the "Add" button after entering text into the text field, should return the user back to the Start Menu. 
- Clicking the "Add" button after entering white space into the field, should change the display label beneath to say, "A name must be entered".
- "Top" label should be located at the top right of the window. 
- "Top" Label should display, "Enter Players Name".
- "Bottom" Label: 
- "Bottom" Label should be located at the Bottom of the window. 
- The "Bottom" label should display, "Please enter name and press add"
- If a player is not added correctly, the "Bottom" label should change its display to, "A name must be entered".
- Text field should be located in the center to the left. 
- Writing into the Text field, should display the letters written. 
- Pressing enter on the keypad while in the Text field should change the display label beneath to say, "A name must be entered".
- Pressing enter on the keypad while in the Text field with letters inside, should return the user back to the Start Menu.   

#### Statistics

- Clicking the Statistics button with no players created should switch the window to: 1 buttons & 1 label. 
- The window layout should default size, the same as Start Menu screen. 
- "Back" button should be located on the top of the window.
- Clicking the "Back" button should return the user back to the start menu. 
- If there are no players entered, beneath the "Back" button a label should display, "A player must be entered before viewing Statistics".
-Clicking the Statistics button with players created should display: 1 button. A series of labels: displaying Wins, Losses and Score. 
- For each player beneath the wins, losses and score labels, should display each players name followed by values for their wins, losses and scores.  
- A ScoreCard label should be displayed beneath all players wins, losses and scores. 
- Displayed beneath ScoreCard should display a label "Holes: " followed by number labels, "|1|" through the amount of holes entered for the game.
-Displayed beneath the "Holes: " label, should be a label for each entered players name. Players 1's name, Players 2's name underneath, Players 3's name underneath and Players 4's name underneath. 
- Displayed followed by Player 1's  names, should be labels for each hole number that places the amount of strokes Players 1 had for that corresponding hole.
- Displayed followed by Player 2's  names, should be labels for each hole number that places the amount of strokes Players 2 had for that corresponding  hole.
- Displayed followed by Player 3's  names, should be labels for each hole number that places the amount of strokes Players 3 had for that corresponding hole.
- Displayed followed by Player 4's  names, should be labels for each hole number that places the amount of strokes Players 4 had for that corresponding hole.    