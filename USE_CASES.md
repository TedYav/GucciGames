USE CASES:

GAME AUTHORING ENVIRONMENT:
* Author initializes newly created game
* Author sets global game properties
* Author sets map image and adds tiles to map
* Author creates custom structure
* Author creates custom tile
* Author creates a custom unit
* Author adds custom property to unit
* Author sets a unit’s display image
* Author tries to initialize a unit in the same tile as another unit
* Author sets a new splash screen
* Author creates a unit that has a special attack based on the type of unit it is attacking
* Author sets abilities to unit
* Author adds another level to the game and defines how that level is loaded (sequentially, user picked, etc)
* Author assigns reactions to interactions (i.e., what happens when a key is pressed or the mouse is moved)
* Author loads previously created games to be edited again or modded

GAME PLAYER:
* Player brings up list of games to choose from
* Player chooses a map to play in chosen game
* Player scrolls around the map
* Player ends turn
* Player saves a gamestate
* Player loads a gamestate
* HUD displays of resources decrements after building a structure
* Player switches games repeatedly without quitting
* Player clicks on a unit to see the available commands (Move and Attack by default)
* Game player draws the map with “fog of war” (tiles you have not seen).

GAME DATA:
* Created game data is saved to file
* Created game data is loaded into editor
* Game data is uploaded to cloud storage
* Games are loaded from cloud storage
* Gamestate written to file
* Gamestate loaded from file
* High scores loaded from cloud

GAME ENGINE:
* Unit moves with custom groovy code attached by author
* Author clicks “Build” in authoring environment to check for compile errors
* Level ends with custom behavior defined by author
* Check if game is complete after player turn ends
* Unit attacks another unit and battle takes place(events in a game)
* Decide where the unit can move
* Decide where the unit can attack.
* Decide if/where a structure can be built.
* Decide which units can be built from the structure
* Decides changing unit/structure images reflecting changes in state (i.e. animations if we implement it)
* Decide on how much of the map has been explored.
* Decide if the unit can use an ability.
* Decide what happens after a battle (death of a unit, hp reduction, additional effects)(turn update)
* Check goal is reached at a level
	

