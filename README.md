ColorPuzzle
===========

Object of the Game
------------------

The playing field consists of 11*11 squares, randomly filled with one of 7 colors.
At the start of the game the player "controls" the square at the top left corner.
By clicking one of the color-buttons on the right he can change the color of all squares he controls.

Every square that has the same color as the player clicks and is in the direct neighborhood of
 one of the squares controlled by the player, is now also under his control.

The goal is to get all squares on the field under your control with as few clicks as possible.

Build the game
--------------
The project is maven based. To build the project simply run `mvn clean install`. 
After this run `mvn assembly:single` to create the executable jar and
package all needed libraries into a `zip` located in the `target` folder.

To play the game, unpack this zip to any location and run the jar-file inside the extracted folder (`java -jar colorPuzzle.jar`).






