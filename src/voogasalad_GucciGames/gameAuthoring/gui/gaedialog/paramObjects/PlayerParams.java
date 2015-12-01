package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;
/**
 * Contains parameters for player
 * 
 * To create a playerperson, need id
 * 
 * create movablePlayer characteristic from numMoves
 * 
 * ignore name param for now
 * 
 * @author yingqi
 *
 */
public class PlayerParams extends AParamsObject{
	
	private int id;
	private String name;
	private int numMoves;
	
	public PlayerParams(int id, String name, int numMoves){
		this.id = id;
		this.name = name;
		this.numMoves = numMoves;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumMoves() {
		return numMoves;
	}

	public void setNumMoves(int numMoves) {
		this.numMoves = numMoves;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}
