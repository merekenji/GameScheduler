package java_game_scheduler;

import static org.junit.Assert.*;
import org.junit.Test;

public class SchedulerTest {

	@Test
	public void createGameSuccessfully() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("Tennis", 2);
		assertEquals("Success: Game has been saved successfully", service.createGame(game));
	}

	@Test
	public void createGameWithNullGameName() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("", 5);
		assertEquals("Error: The Game name should not be empty", service.createGame(game));
	}

	@Test
	public void createGameWithNoPlayers() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("Golf", 0);
		assertEquals("Error: There should at least be 1 player playing in the game", service.createGame(game));
	}

	@Test
	public void createDuplicateGame() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("Tennis", 2);
		service.createGame(game);
		assertEquals("Error: Game has already exist", service.createGame(game));
	}

	@Test
	public void createNullGame() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Game object is null", service.createGame(null));
	}

	@Test
	public void createPlayerSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		Game game1 = new Game("Tennis", 2);
		Game game2 = new Game("Football", 11);
		Game game3 = new Game("Badminton", 2);
		service.createGame(game1);
		Game[] games = { game1, game2, game3 };
		Player player = new Player("Tom", games);
		assertEquals("Success: Player has been saved successfully", service.createPlayer(player));
	}

	@Test
	public void createPlayerThatPlayNoGames() {
		ISchedulerService service = new SchedulerService();

		Game game1 = new Game("Tennis", 2);
		Game game2 = new Game("Football", 11);
		Game game3 = new Game("Badminton", 2);
		Game[] games = { game1, game2, game3 };
		Player player = new Player("Tom", games);
		assertEquals("Error: At least 1 game should exist in game repo", service.createPlayer(player));
	}

	@Test
	public void createPlayerWithNoName() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("Tennis", 2);
		service.createGame(game);
		Game[] games = { game };
		Player player = new Player("", games);
		assertEquals("Error: The Player name should not be empty", service.createPlayer(player));
	}

	@Test
	public void createDuplicatePlayer() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("Tennis", 2);
		service.createGame(game);
		Game[] games = { game };
		Player player = new Player("Tom", games);
		service.createPlayer(player);
		assertEquals("Error: Player has already exist", service.createPlayer(player));
	}

	@Test
	public void createNullPlayer() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Player object is null", service.createPlayer(null));
	}

	@Test
	public void createDaySuccessfully() {
		ISchedulerService service = new SchedulerService();

		Game game1 = new Game("Tennis", 2);
		Game game2 = new Game("Basketball", 5);
		service.createGame(game1);
		service.createGame(game2);
		Game[] games = { game1, game2 };
		Day day = new Day("Day One", games);
		assertEquals("Success: Day has been saved successfully", service.createDay(day));
	}

	@Test
	public void createDayWithNoGamesInRepo() {
		ISchedulerService service = new SchedulerService();

		Game game1 = new Game("Tennis", 2);
		Game game2 = new Game("Basketball", 5);
		Game[] games = { game1, game2 };
		Day day = new Day("Day One", games);
		assertEquals("Error: All game should exist in game repo", service.createDay(day));
	}

	@Test
	public void createDayWithoutName() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("Tennis", 2);
		service.createGame(game);
		Game[] games = { game };
		Day day = new Day("", games);
		assertEquals("Error: The Day name should not be empty", service.createDay(day));
	}

	@Test
	public void createDuplicateDay() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("Tennis", 2);
		service.createGame(game);
		Game[] games = { game };
		Day day = new Day("Day One", games);
		service.createDay(day);
		assertEquals("Error: Day has already exist", service.createDay(day));
	}

	@Test
	public void createNullDay() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: The Day object is null", service.createDay(null));
	}

	@Test
	public void generateGameReportSuccessfully() {
		ISchedulerService service = new SchedulerService();

		Game game = new Game("Basketball", 5);
		service.createGame(game);
		Game[] games = { game };
		Player player1 = new Player("Tom", games);
		Player player2 = new Player("Jerry", games);
		service.createPlayer(player1);
		service.createPlayer(player2);
		Day day = new Day("Day One", games);
		service.createDay(day);

		StringBuffer sb = new StringBuffer();
		sb.append("Game Report for Basketball\n");
		sb.append("No. of Players: 5\n\n");
		sb.append("Players playing in this game\n");
		sb.append("Tom\n");
		sb.append("Jerry\n");
		sb.append("Days game is scheduled on\n");
		sb.append("Day One\n");
		assertEquals(sb.toString(), service.gameWiseReport("Basketball").toString());
	}

	@Test
	public void generateNonExistantGameReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Game does not exist", service.gameWiseReport("Tennis").toString());
	}

	@Test
	public void generateEmptyGameReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Game name should not be empty", service.gameWiseReport("").toString());
	}

	@Test
	public void generatePlayerReportSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		Game game = new Game("Basketball", 5);
		service.createGame(game);
		Game[] games = { game };
		Player player1 = new Player("Tom", games);
		service.createPlayer(player1);
		Day day = new Day("Day One", games);
		service.createDay(day);
		
		StringBuffer sb = new StringBuffer();
		sb.append("Player Report for Tom\n\n");
		sb.append("Games player is playing in:\n");
		sb.append("Basketball\n");
		sb.append("Days Game is scheduled on\n");
		sb.append("Day One\n");
		
		assertEquals(sb.toString(), service.playerWiseReport("Tom").toString());
	}

	@Test
	public void generateNonExistantPlayerReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Player does not exist", service.playerWiseReport("Tom").toString());
	}

	@Test
	public void generateEmptyPlayerReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Player name should not be empty", service.playerWiseReport("").toString());
	}

	@Test
	public void generateDayReportSuccessfully() {
		ISchedulerService service = new SchedulerService();
		
		Game game = new Game("Basketball", 5);
		service.createGame(game);
		Game[] games = { game };
		Player player1 = new Player("Tom", games);
		service.createPlayer(player1);
		Day day = new Day("Day One", games);
		service.createDay(day);
		
		StringBuffer sb = new StringBuffer();
		sb.append("Day Report for Day One\n\n");
		sb.append("Games played on this day\n");
		sb.append("Basketball\n");
		sb.append("Players playing in this game\n");
		sb.append("Tom\n");
		
		assertEquals(sb.toString(), service.dayWiseReport("Day One").toString());
	}

	@Test
	public void generateNonExistantDayReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Day does not exist", service.dayWiseReport("Day One").toString());
	}

	@Test
	public void generateEmptyDayReport() {
		ISchedulerService service = new SchedulerService();

		assertEquals("Error: Day name should not be empty", service.dayWiseReport("").toString());
	}
	
	@Test
	public void generateGameReportThatAreNotScheduledOnAnyDays() {
		ISchedulerService service = new SchedulerService();
		
		Game game = new Game("Basketball", 5);
		service.createGame(game);
		Game[] games = { game };
		Player player1 = new Player("Tom", games);
		Player player2 = new Player("Jerry", games);
		service.createPlayer(player1);
		service.createPlayer(player2);
		
		assertEquals("Error: Game not scheduled on any day", service.gameWiseReport("Basketball").toString());
	}
	
	@Test
	public void generateGameReportThatHasNoPlayers() {
		ISchedulerService service = new SchedulerService();
		
		Game game = new Game("Basketball", 5);
		service.createGame(game);
		Game[] games = { game };
		Day day = new Day("Day One", games);
		service.createDay(day);
		
		assertEquals("Error: Game does not have any players", service.gameWiseReport("Basketball").toString());
	}
	
}