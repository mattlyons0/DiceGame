### The following are Tests for the UI that the QA team would do to ensure the UI elements are working as expected:

#### Overall

- Upon launching the application a window should pop up
- This window should be sized appropriately (excluding HI-DPI devices, swing cannot change DPI resolution) as to not cut off or shrink any elements on the screen
- Clicking the Close button on the window should close the java application and window

#### Main Menu

- There should be a Start Game button
- Clicking the Start Game button should maximize the window
- Clicking the Start Game button should switch to a different view (see Game Screen)
- There should be a Create New Player button
- Clicking Create New Player button should switch to a different view (see Player Creation)
- There should be a Statistics button
- The statistics button should be greyed out and should not respond to clicks
- There should be a Quit Game button
- Clicking the Quit Game button should close the window and the java application
- Upon the bottom of the of the screen there should be a label stating either no players are created or listing the created players
- Upon creating a new player, this player's name should be added to the list of players at the bottom of the Main Menu screen
- There should be a comma in between every player's name if there are more than one

#### Game Screen

- Clicking the Start Game button should show 3 layout elements
- There should be a border at the top of the window with the text 'A visualization will go here in the next sprint.'
- The layout should take up approximately 40% of the screen
- There should be a Table in the middle of the screen vertically
- The table should take up approximately 40% of the screen
- The table should contain 3 headers, Name, Turns and Distance from Hole
- There should be a border inset for the bottom element taking up approximately 15-20% of the screen
- The bottom element should contain the controls for the game (Roll Dice Multiplier or Dice Roll buttons)
- There should be a label at the top of the element stating which player's turn it is
- When Roll Dice Multiplier is clicked 1-6 dice should appear with the label 'Roll'
- Upon clicking these roll buttons nothing should currently happen

#### Player Creation

- David's Part

#### Statistics

- NYI