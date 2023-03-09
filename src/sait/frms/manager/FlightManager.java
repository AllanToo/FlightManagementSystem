package sait.frms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.table.TableStringConverter;

import sait.frms.gui.FlightsTab;
import sait.frms.problemdomain.Flight;

public class FlightManager {
	private File airportfile = new File("res/airports.csv");
	private File flightsfile = new File("res/flights.csv");
	private ArrayList<Flight> flights = new ArrayList<>();
	private ArrayList<String> airports = new ArrayList<>();
	public String WEEKDAY_ANY;
	public String WEEKDAY_SUNDAY;
	public String WEEKDAY_MONDAY;
	public String WEEKDAY_TUESDAY;
	public String WEEKDAY_WEDNESDAY;
	public String WEEKDAY_THURSDAY;
	public String WEEKDAY_FRIDAY;
	public String WEEKDAY_SATURDAY;

	public FlightManager() throws FileNotFoundException {
		populateFlights();
		populateAirports();

	}

	public ArrayList<Flight> getFlights() throws FileNotFoundException {
		Scanner in = new Scanner(flightsfile);
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] fields = line.split(",");
			String flightcode = fields[0];
			String fromairport = fields[1];
			String toairport = fields[2];
			String weekday = fields[3];
			
			String time = fields[4];
			int seats = Integer.parseInt(fields[5]);
			double costperseat = Double.parseDouble(fields[6]);
			Flight flight = new Flight(flightcode, fromairport, toairport, weekday, time, seats, costperseat);
			flights.add(flight);

		}
		in.close();

		return flights;
	}

	public ArrayList<String> getAirports() throws FileNotFoundException {
		Scanner in = new Scanner(airportfile);
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] fields = line.split(",");

			String airlineCode = fields[0] ;
			String airline = fields[1];
			airports.add(airlineCode);
		}
		in.close();

		return airports;
	}

	public String findAirportByCode(String code) {
		return code;
	}

	public Flight findFlightByCode(String code) {
		return null;
	}

	public ArrayList<Flight> findFlights(String from, String to, String weekday) throws FileNotFoundException {
		
		ArrayList<Flight> arrayOffilght = new  ArrayList<>();
	

	
		
		for (Flight flight : getFlights()) { // loop to check for every flight object
		
			
			String depart = flight.getFrom();
			String arrival = flight.getTo();
			String inputDate = flight.getWeekday();
			if ( from.equalsIgnoreCase(depart) && to.equalsIgnoreCase(arrival) ) {
				arrayOffilght.add(flight);
			}
			else if (from.equalsIgnoreCase(depart) && to.equalsIgnoreCase(arrival) && weekday.equalsIgnoreCase(inputDate)) {
				arrayOffilght.add(flight);
			
				
			}
			
		
		
		}
		return arrayOffilght;
	}
				
	

	

	private void populateFlights() throws FileNotFoundException {

	}

	private void populateAirports() throws FileNotFoundException {

	}

}
