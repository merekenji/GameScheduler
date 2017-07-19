package assignment1;

public class DayRepo implements IDayRepo {

	private Day[] days;
	
	public DayRepo() {
		days = new Day[5];
	}
	
	public DayRepo(Day[] days) {
		this.days = days;
	}
	
	public String save(Day d) {
		if(d.getName().equals("") || d.getName() == null) {
			return "Error: The Day name should not be empty";
		} else {
			if(checkExist(d)) {
				return "Error: Day has already exist";
			}
			if(checkFilled()) {
				addSpace();
			}
			addDay(d);
			return "Success: Day has been saved successfully";
		}
	}
	
	public boolean checkExist(Day d) {
		boolean exist = false;
		for(Day day : days) {
			if(day != null && day.getName().equals(d.getName())) {
				exist = true;
			}
		}
		return exist;
	}
	
	public boolean checkFilled() {
		boolean filled = true;
		for(Day day : days) {
			if(day == null) {
				filled = false;
			}
		}
		return filled;
	}
	
	public void addSpace() {
		Day[] temp = days;
		days = new Day[temp.length+5];
		for(int i=0; i<temp.length; i++) {
			days[i] = temp[i];
		}
	}
	
	public void addDay(Day d) {
		for(int i=0; i<days.length; i++) {
			if(days[i] == null) {
				days[i] = d;
				break;
			}
		}
	}

	public Day findOne(String name) {
		for(Day day : days) {
			if(day != null && day.getName().equals(name)) {
				return day;
			}
		}
		return null;
	}

	public Day[] findAll() {
		return days;
	}

}
