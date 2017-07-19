package assignment1;

public class GameRepo implements IGameRepo {
	
	private Game[] games;
	
	public GameRepo() {
		games = new Game[5];
	}
	
	public GameRepo(Game[] games) {
		this.games = games;
	}
	
	public String save(Game g) {
		if(g == null) {
			return "Error: The Game object is null";
		} else if(g.getName().equals("") || g.getName() == null) {
			return "Error: The Game name should not be empty";
		} else if(g.getNoOfPlayers() <= 0) {
			return "Error: There should at least be 1 player playing in the game";
		} else {
			if(checkExist(g)) {
				return "Error: Game has already exist";
			}
			if(checkFilled()) {
				addSpace();
			}
			addGame(g);
			return "Success: Game has been saved successfully";
		}
	}
	
	public boolean checkExist(Game g) {
		boolean exist = false;
		for(Game game : games) {
			if(game != null && game.getName().equals(g.getName())) {
				exist = true;
			}
		}
		return exist;
	}
	
	public boolean checkFilled() {
		boolean filled = true;
		for(Game game : games) {
			if(game == null) {
				filled = false;
			}
		}
		return filled;
	}
	
	public void addSpace() {
		Game[] temp = games;
		games = new Game[temp.length+5];
		for(int i=0; i<temp.length; i++) {
			games[i] = temp[i];
		}
	}
	
	public void addGame(Game g) {
		for(int i=0; i<games.length; i++) {
			if(games[i] == null) {
				games[i] = g;
				break;
			}
		}
	}

	public Game findOne(String name) {
		for(Game game : games) {
			if(game != null && game.getName().equals(name)) {
				return game;
			}
		}
		return null;
	}

	public Game[] findAll() {
		return games;
	}

}
