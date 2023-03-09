package sait.frms.problemdomain;

public class Reservation {
	private String code;
	private String flightCode;
	private double cost;
	private String name;
	private String citizenship;
	private boolean active;

	
	


	public Reservation(String code, String flightCode, Double cost, String name, String citizenship,
			boolean active) {
		this.code = code;
		this.flightCode = flightCode;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}


	public String getCode() {
		return code;
	}

	public String getFlightCode() {
		return flightCode;
	}



	public String getName() {
		return name;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public double getCost() {
		return cost;
	}

	public boolean isActive() {
		return active;
	}
    
	public void setName(String name) {
		this.name = name;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	
	public void setActive( boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Reservation [code=" + code + ", flightCode=" + flightCode +  ", name=" + name
				+ ", citizenship=" + citizenship + ", cost=" + cost + ", active=" + active + "]";
	}
	
}
