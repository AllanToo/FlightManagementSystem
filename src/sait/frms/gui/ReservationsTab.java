package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotActiveException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	private JList<Reservation> reservationsList;
	private DefaultListModel<Reservation> reservationModel;
	JTextField codeField;
	JTextField airLineField;
	JTextField flightField;
	JTextField costField;
	JTextField nameField;
	JTextField citizenshipField;
	String [] isActive = {"Active", "Not Active"};
	
	
	
	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centrePanel = createCenterPanel();
		panel.add(centrePanel, BorderLayout.CENTER);
	
		
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		
		
	
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
   
	
	private JPanel createEastPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel upper = new JPanel();
		upper.setLayout(new FlowLayout());
		JPanel middle = new JPanel();
		middle.setLayout(new GridBagLayout());
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		
		//upper
		JLabel title = new JLabel("Reserve");
		title.setFont(new Font("serif", Font.PLAIN, 29));
		upper.add(title);
		
		//middle
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		middle.add(codeLabel, c);
		codeField = new JTextField(10);
		codeField.setEditable(false);
		c.gridx = 1;
		c.gridy = 0;
		middle.add(codeField, c);

		JLabel flightLabel = new JLabel("Flight:");
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		c.gridx = 0;
		c.gridy = 1;
		middle.add(flightLabel, c);
		 flightField = new JTextField(10);
		 flightField.setEditable(false);
		
		c.gridx = 1;
		c.gridy = 1;
		middle.add(flightField, c);
		
		JLabel airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		middle.add(airlineLabel, c);
		 airLineField = new JTextField(10);
		 airLineField.setEditable(false);
		c.gridx = 1;
		c.gridy = 2;
		middle.add(airLineField, c);
		
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		middle.add(costLabel, c);
		 costField = new JTextField(10);
		 costField.setEditable(false);
		c.gridx = 1;
		c.gridy = 3;
		middle.add(costField, c);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		middle.add(nameLabel, c);
		 nameField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 4;
		middle.add(nameField, c);
		
		JLabel citizenshipLabel = new JLabel("Citizenship:");
		citizenshipLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 5;
		middle.add(citizenshipLabel, c);
		 citizenshipField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 5;
		middle.add(citizenshipField, c);
		
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 6;
		middle.add(statusLabel, c);
		JComboBox selectstatus = new JComboBox<>(isActive);
		c.gridx = 1;
		c.gridy = 6;
		middle.add(selectstatus, c);
		
		//bottom
		JButton updateButton = new JButton("Update");
		bottom.add(updateButton);
		updateButton.setPreferredSize(new Dimension(200, 30));
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (nameField.getText().equals("") || citizenshipField.getText().equals("")  ) {
		    	    	JOptionPane.showMessageDialog(null, "Error! Please enter required field to update your information");
		    	    }
		    	    	else {
		    	    		
					     
					        try {
					        	  JOptionPane.showMessageDialog(null, "Your information has been updated");
								reservationManager.updateReservation(reservationsList.getSelectedValue().getCode(),
										reservationsList.getSelectedValue(), nameField.getText(), citizenshipField.getText());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					
		    	    	}
					    
				
			}
				
			
		});
		
		
		//add panels to main panel
		panel.add(upper,BorderLayout.NORTH);
		panel.add(middle,BorderLayout.CENTER);
		panel.add(bottom,BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout());
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridBagLayout());
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		//upper
		JLabel titleLabel = new JLabel("Search");
		titleLabel.setFont(new Font("serif", Font.PLAIN, 29));
		upperPanel.add(titleLabel);
		
		//middle
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		middlePanel.add(codeLabel, c);
		JTextField codeField = new JTextField();
		c.gridx = 1;
		c.gridy = 0;
		middlePanel.add(codeField, c);
		codeField.setPreferredSize(new Dimension(500, 30));
		
		JLabel airlinelabel = new JLabel("Airline:");
		airlinelabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		middlePanel.add(airlinelabel, c);
		JTextField airlineField = new JTextField();
		c.gridx = 1;
		c.gridy = 1;
		middlePanel.add(airlineField, c);
		airlineField.setPreferredSize(new Dimension(500, 30));
 
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		middlePanel.add(nameLabel, c);
		JTextField nameField = new JTextField();
		c.gridx = 1;
		c.gridy = 2;
		middlePanel.add(nameField, c);
		nameField.setPreferredSize(new Dimension(500, 30));
		
		//bottom
		JButton findReservationssButton = new JButton("Find Reservations");
		bottomPanel.add(findReservationssButton);
		findReservationssButton.setPreferredSize(new Dimension(550, 30));
		findReservationssButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputCode =   codeField.getText();
			    String inputAirLine = airlineField.getText();
				String inputName = nameField.getText();
				
			
			  
			    for(Reservation r: reservationManager.findReservations(inputCode, inputAirLine, inputName))
			    {
			    	reservationModel.addElement(r);
			    }
				
				
			}
		});
		//add panels to main panel
		panel.add(upperPanel,BorderLayout.NORTH);
		panel.add(middlePanel,BorderLayout.CENTER);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		reservationModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationModel);
		
		
		// User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		
		return panel;
	}
	
	
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			Reservation reservation = reservationsList.getSelectedValue();
			 codeField.setText(reservation.getCode());
			 flightField.setText(reservation.getFlightCode());
			 costField.setText(reservation.getCost() + "");
		}
		
	}
	
}
