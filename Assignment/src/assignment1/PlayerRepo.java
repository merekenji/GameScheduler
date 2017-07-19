package assignment1;

public class PlayerRepo implements IPlayerRepo {

	private Player[] players;

	public PlayerRepo() {
		players = new Player[5];
	}

	public PlayerRepo(Player[] players) {
		this.players = players;
	}

	public String save(Player p) {
		if (p.getName().equals("") || p.getName() == null) {
			return "Error: The Player name should not be empty";
		} else {
			if (checkExist(p)) {
				return "Error: Player has already exist";
			}
			if (checkFilled()) {
				addSpace();
			}
			addPlayer(p);
			return "Success: Player has been saved successfully";
		}
	}

	public boolean checkExist(Player p) {
		boolean exist = false;
		for (Player player : players) {
			if (player != null && player.getName().equals(p.getName())) {
				exist = true;
			}
		}
		return exist;
	}

	public boolean checkFilled() {
		boolean filled = true;
		for (Player player : players) {
			if (player == null) {
				filled = false;
			}
		}
		return filled;
	}

	public void addSpace() {
		Player[] temp = players;
		players = new Player[temp.length + 5];
		for (int i = 0; i < temp.length; i++) {
			players[i] = temp[i];
		}
	}

	public void addPlayer(Player p) {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) {
				players[i] = p;
				break;
			}
		}
	}

	public Player findOne(String name) {
		for (Player player : players) {
			if (player != null && player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

	public Player[] findAll() {
		return players;
	}

}
