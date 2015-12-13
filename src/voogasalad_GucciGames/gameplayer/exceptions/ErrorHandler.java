package voogasalad_GucciGames.gameplayer.exceptions;

public class ErrorHandler {

	public static void handleError(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("I SHOULD POP UP A BOX HERE");
	}

}
