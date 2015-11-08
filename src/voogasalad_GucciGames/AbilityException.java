package voogasalad_GucciGames;

//this is the exception that will be thrown on the backend to let front-end know that user tried an illegal move. We will give you the reason in a String format

public class AbilityException extends Exception {

	private String myReason;
	
	public AbilityException(String reason){
		myReason = reason;
	}
	
	public String getReason(){
		return myReason;
	}
}
