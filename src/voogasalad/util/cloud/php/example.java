// package voogasalad.util.cloud;
//
// public class CloudExample {
//
// public static void main(String[] args){
//
// Cloud c = new Cloud();
// String gameName = "Duvall Tag";
// String playerName = "Superman";
// double score = 99999999;
//
//
//
// // It's this easy to add a high score!
//
// c.addHighScore(gameName, playerName, score);
// System.out.println(c.retrieveHighScores(gameName));
//
//
//
//
//
// // WILL BE IMPLEMENTED LATER
// List<String> gameFiles = Arrays.asList(
// "main.xml",
// "image1.jpg",
// "/resources/sound.wav");
// String target = "/voogasalad/my_game_folder/"
//
// c.saveGameData(gameName, gameFiles);
// c.retrieveGameData(gameName, target);
// // returns List<String> of files downloaded
//
// List<String> saveFiles = Arrays.asList("state.xml");
// String saveName = "first save";
// c.saveGameState(gameName, playerName, saveName, saveFiles);
//
// List<String> mySaves = c.retrieveGameStates();
// c.retrieveGameState(gameName, playerName, mySaves.get(0));
// // returns List<String> of game state files
//
// }
//
// }