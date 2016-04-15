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

- NYI