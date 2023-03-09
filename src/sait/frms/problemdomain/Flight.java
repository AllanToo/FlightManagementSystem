package sait.frms.problemdomain;

public class Flight {
	private String code;
	
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;

	public Flight() {

	}
	

	public Flight(String code, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		super();
		this.code = code;
	
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}


	public String getCode() {
		return code;
	}

	
	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getWeekday() {
		return weekday;
	}

	public String getTime() {
		return time;
	}

	public int getSeats() {
		return seats;
	}

	public double getCostPerSeat() {
		return costPerSeat;
	}
   
	public boolean isDomestic() {
		return false;
		
	}
	
	public void parseCode(String code) {
		
	}

	@Override
	public String toString() {
		return "Code:" + code + ", From:" + from + ", To:" + to + ", Day:"
				+ weekday + ", Time:" + time + ", Cost:" + costPerSeat ;
	}
	
	
	
}
