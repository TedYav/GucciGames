package voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents;

public class TargetCoordinate {

	private int myX;
	private int myY;
	
	public TargetCoordinate(int x, int y){
		myX = x; myY = y;
	}
	
	public int getX(){
		return myX;
	}
	
	public int getY(){
		return myY;
	}
	
}
