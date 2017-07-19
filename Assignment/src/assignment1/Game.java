package assignment1;

public class Game {
	
	private String name;
	private int noOfPlayers;
	
	public Game() {
		name = "";
		noOfPlayers = 0;
	}
	
	public Game(String name, int noOfPlayers) {
		this.name = name;
		this.noOfPlayers = noOfPlayers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
	
}
