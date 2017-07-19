package assignment1;

public class SchedulerService implements ISchedulerService {

	private GameRepo gameRepo;
	private PlayerRepo playerRepo;
	private DayRepo dayRepo;

	public SchedulerService() {
		gameRepo = new GameRepo();
		playerRepo = new PlayerRepo();
		dayRepo = new DayRepo();
	}

	public SchedulerService(GameRepo gameRepo, PlayerRepo playerRepo, DayRepo dayRepo) {
		this.gameRepo = gameRepo;
		this.playerRepo = playerRepo;
		this.dayRepo = dayRepo;
	}

	public String createGame(Game g) {
		return gameRepo.save(g);
	}

	public String createPlayer(Player p) {
		boolean exist = false;
		if (p != null) {
			Game[] games = p.getGames();
			for (Game g : games) {
				if (gameRepo.findOne(g.getName()) != null) {
					exist = true;
					break;
				}
			}
		} else {
			return "Error: The Player object is null";
		}
		if (!exist) {
			return "Error: At least 1 game should exist in game repo";
		}
		return playerRepo.save(p);
	}

	public String createDay(Day d) {
		boolean exist = true;
		if (d != null) {
			Game[] games = d.getGames();
			for (Game g : games) {
				if (gameRepo.findOne(g.getName()) == null) {
					exist = false;
					break;
				}
			}
		} else {
			return "Error: The Day object is null";
		}
		if (!exist) {
			return "Error: All game should exist in game repo";
		}
		return dayRepo.save(d);
	}

	public StringBuffer gameWiseReport(String gameName) {
		StringBuffer sb = new StringBuffer();
		if (gameName.equals("") || gameName == null) {
			return sb.append("Error: Game name should not be empty");
		} else {
			Game game = gameRepo.findOne(gameName);
			if (game == null) {
				return sb.append("Error: Game does not exist");
			} else {
				sb.append("Game Report for " + game.getName() + "\n");
				sb.append("No. of Players: " + game.getNoOfPlayers() + "\n\n");

				sb.append("Players playing in this game\n");
				for (Player p : playerRepo.findAll()) {
					if (p != null) {
						for (Game g : p.getGames()) {
							if (g.getName().equals(gameName)) {
								sb.append(p.getName() + "\n");
								break;
							}
						}
					}
				}

				sb.append("Days game is scheduled on\n");
				for (Day d : dayRepo.findAll()) {
					if (d != null) {
						for (Game g : d.getGames()) {
							if (g.getName().equals(gameName)) {
								sb.append(d.getName() + "\n");
								break;
							}
						}
					}
				}
			}
		}
		return sb;
	}

	public StringBuffer playerWiseReport(String playerName) {
		StringBuffer sb = new StringBuffer();
		if (playerName.equals("") || playerName == null) {
			return sb.append("Error: Player name should not be empty");
		} else {
			Player player = playerRepo.findOne(playerName);
			if (player == null) {
				return sb.append("Error: Player does not exist");
			} else {
				sb.append("Player Report for " + player.getName() + "\n\n");
				sb.append("Games player is playing in:\n");
				for(Game g : player.getGames()) {
					if(g != null) {
						Game game = gameRepo.findOne(g.getName());
						
						if(game != null) {
							sb.append(game.getName() + "\n");
							sb.append("Days Game is scheduled on\n");
							for(Day d : dayRepo.findAll()) {
								if(d != null) {
									for(Game dayGame : d.getGames()) {
										if(dayGame.getName().equals(game.getName())) {
											sb.append(d.getName() + "\n");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return sb;
	}

	public StringBuffer dayWiseReport(String dayName) {
		StringBuffer sb = new StringBuffer();
		if (dayName.equals("") || dayName == null) {
			return sb.append("Error: Day name should not be empty");
		} else {
			Day day = dayRepo.findOne(dayName);
			if (day == null) {
				return sb.append("Error: Day does not exist");
			} else {
				sb.append("Day Report for " + day.getName() + "\n\n");
				sb.append("Games played on this day\n");
				for(Game g : day.getGames()) {
					if(g != null) {
						Game game = gameRepo.findOne(g.getName());
						
						if(game != null) {
							sb.append(game.getName() + "\n");
							sb.append("Players playing in this game\n");
							for(Player p : playerRepo.findAll()) {
								if(p != null) {
									for(Game playerGame : p.getGames()) {
										if(playerGame.getName().equals(game.getName())) {
											sb.append(p.getName() + "\n");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return sb;
	}

}
