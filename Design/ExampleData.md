**Write any number of data files needed to represent one, simple, game. The goal of these example files (such as property files, XML files, and other resource files like HTML pages, images, or sounds) is to help the team agree to standard locations and formats for your shared data. Note, your example does not need to include all the data fields that will be needed for all objects in the game, just to be representative of the file's format.**  

The main data of GucciGame has two parts, map data and game progress. Map data is created by Game Authoring Environment, containing information crucial to the functionality of the game, such as size of the grid, initial position of units, goal of the game and initial conditions. Game stats stores the progress of the game, containing a snapshot of resources, players' positions and other important game information at the time when the game is saved.  

Xstream handles saving map data. When game author finishes editing a game in the game authoring environment and clicks save, back end of GAE will call Xstream to store all map objects in a single xml file. The xml file will be placed in the same folder as the supporting files of that game such as images.Xstream also handles saving game progress. Xstream will serialize the entire engine into an xml file to preserve integrity of the game.  

An example of Xstream can be found on https://github.com/duke-compsci308-fall2015/voogasalad_GucciGames/tree/master/examples/XStreamExample.

Peripheral data such as texts of button and menu items will be stored in property files. Images will be in jpg, png or gif format.
