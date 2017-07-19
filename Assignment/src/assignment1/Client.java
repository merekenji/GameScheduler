package assignment1;

public class Client {

	public static void main(String[] args) {
		SchedulerService service = new SchedulerService();
		
		Game game1 = new Game("Football", 11);
		service.createGame(game1);
	}

}
