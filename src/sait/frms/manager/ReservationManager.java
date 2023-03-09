package sait.frms.manager;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

public class ReservationManager {
	private static final String BINARY_FILE = "res/reservations.bin";
	
	private static final String MODE = "rw";
	private static final int RESERVATION_SIZE = 237;
	
	private ArrayList<Reservation> reservations = new ArrayList<>();
	private RandomAccessFile raf;
	Reservation rs ;

	
	public ReservationManager() {
		
	}
	
	public Reservation makeReservation(String codeGenerate,Flight flight, String name, String citizenship) throws IOException {
		
		String code = codeGenerate;
		String flightCode = flight.getCode();
		Double cost = flight.getCostPerSeat();
		String nameCustomer = name;
		String citizenshipCustomer = citizenship;
		boolean active = true;
	    rs = new Reservation(code, flightCode, cost, nameCustomer, citizenshipCustomer, active);
		persist();
	
	
		return rs;
		
	}
public Reservation updateReservation(String codeGenerate,Reservation reservation, String name, String citizenship) throws IOException {
		
		String code = codeGenerate;
		String flightCode = reservation.getFlightCode();
		Double cost = reservation.getCost();
		String nameCustomer = name;
		String citizenshipCustomer = citizenship;
		boolean active = true;
	    rs = new Reservation(code, flightCode, cost, nameCustomer, citizenshipCustomer, active);
		persist();
	    return rs;
}
	
	
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {

		
		try {
			raf.seek(0);
		      for (long position = 0; position < this.raf.length(); position += RESERVATION_SIZE) {
				
				Reservation res = populateFromBinary();
				
				if (res.getName().contains(name)) {
					 reservations.add(res);
				}
			}
		} catch (IOException e) {
		      
		}
		catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Please enter correct code");
		}
		
		
		return reservations;
		
	}
	
	public Reservation findReservationByCode(String code) {
		return null;
		
	}
	
	public void persist() throws IOException {
	
		this.raf = new RandomAccessFile(BINARY_FILE, MODE);
		String code = String.format("%-10s",rs.getCode() ); //12
		
		this.raf.writeUTF(code);
		
	
		String flightCode = String.format("%-10s", rs.getFlightCode());
		this.raf.writeUTF(flightCode); // 12
		
		
		
		
		this.raf.writeDouble(rs.getCost());//8
		
		String name = String.format("%-100s", rs.getName());
		this.raf.writeUTF(name);//102
		
		String citizen = String.format("%-100s", rs.getCitizenship());
		this.raf.writeUTF(citizen);//102
		
		this.raf.writeBoolean(true);//1
		
		
	}
	
	private int getAvailableSeats(Flight flight) {
		return 0;
		
	}
	
	public String generateReservationCode(Flight flight) {
		String reservationCode = " ";
		int randomNumber = new Random().nextInt(9000) + 1000;
		if (flight.getFrom().charAt(0) == 'Y') {
			reservationCode = "D" + randomNumber;
		}
		else {
			reservationCode = "I" +  randomNumber ;
			
		}
		return reservationCode;
		
	}
	
	private Reservation populateFromBinary() throws IOException {
		
		String code = this.raf.readUTF().trim();
		String flightCode = this.raf.readUTF().trim();
		double cost = this.raf.readDouble();
		String name = this.raf.readUTF().trim();
		String citizen = this.raf.readUTF().trim();
		boolean active = this.raf.readBoolean();
		
		
		 rs = new Reservation(code, flightCode, cost, name, citizen, active);
		 return rs;
		
	}
	
}
