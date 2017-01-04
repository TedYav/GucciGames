# Demo [Click Here to Download Archive](https://www.dropbox.com/s/hnf0bord6y7fr9o/VOOGASaladRunnable.zip?dl=0)

For whatever reason, the code here on Github does not run (as I'm guessing someone pushed broken code to master during our analysis period.) I've thus created a working archive of the code that's a slightly earlier build. With this build, one can create games, and one can play games. However, we never did fully get the creating a game and loading it in the game player functionality working. However, there are two sample "games" to play with.

Additionally, the High Scores functionality will not work, and will crash the game. Saving / Loading may do the same, because the server for the cloud utility is no longer running.

In order to run the project, use Eclipse Mars (which is what it is tested under). It'll likely work under a future version but cannot be sure.

Download archive, go to Import -> Existing Project. Select the archive. There are two run configurations included -- Game Authoring and Game Player. If you do not see these run configurations, you can either manually launch the `PlayerMain.java` or `GaeMain.java` classes, or create new Java Application run configurations for them. Cheers!

##README

* Date started: November 1st
* Date finished: December 9th
* Estimated Number of Hours: On the order of hundreds (Assuming an average input of 15 hours per person, 10x15x4 = 600)
* Names/Roles:
 - Efe Aras: Team Lead, Main engine architecture, Networking
 - Ted Yavuzkurt: Game Player main design and implementation of scenes, Cloud Utility, Resource Management Utilities, work on GameData 
 - Ying Qi: MVC architecture for Game Authoring Environment, Dialogs for Game Component properties.
 - John Dai: Game Player GUI components design, system integration centering on Game Data functionality and API 
 - Mike Ma: Front end design for Game Authoring Environment, official Git blamer, most interesting commit message award
 - Daniel McKee: Back end of game authoring environment, including model and factories to transfer data into savable object
 - Joy Patel: Back end design for Game Engine (Events for MapObjects, Groovy Extension)
 - Sally Merza: back end design and implementation for outcomes, rules, conditions.  design and implementtion for factories in the controller
 - Karen Li: Front end design of Game Authoring Environment
 - Tina Liang: game engine, levels implementation
* Files used to start the project: GucciGames.java
* Any data or resource files required by the project: They are in the resources folder
* Information about the program: 
* Any known bugs, crashes or problems with the project's functionality: Note: after cloning the repository if running the Game Player gives a Hash Failed exception for preexisting games, this is likely due to a leftover part of encryption from someelse's local repository. To fix this either run Civ.java or DemoMaker.java in the demo package to reencode the corresponding XML file or create/load a new game from the Authoring Environment and save it.
* Extra features: Networking, Groovy
