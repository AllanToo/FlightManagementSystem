package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {
	String[] days = { "Any", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
	String rescode;

	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */

	private JTextField flightField;
	private JTextField airlineField;
	private JTextField dayField;
	private JTextField timeField;
	private JTextField costField;
	private JTextField nameField;
	private JTextField citizenField;
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 * @throws FileNotFoundException
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) throws FileNotFoundException {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout());
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridBagLayout());
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());

		// upper
		JLabel titleLabel = new JLabel("Reserve");
		titleLabel.setFont(new Font("serif", Font.PLAIN, 29));
		upperPanel.add(titleLabel);

		// middle
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel flightLabel = new JLabel("Flight:");
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		middlePanel.add(flightLabel, c);
		flightField = new JTextField(10);
		flightField.setEditable(false);
		c.gridx = 1;
		c.gridy = 0;
		middlePanel.add(flightField, c);

		JLabel airlineLabel = new JLabel("Airline:");
		airlineLabel.setHorizontalAlignment(JLabel.RIGHT);
		// c.weightx = 0.5;
		// c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 1;
		middlePanel.add(airlineLabel, c);
		airlineField = new JTextField(10);
		// c.weightx = 0.5;
		// c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		middlePanel.add(airlineField, c);

		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		middlePanel.add(dayLabel, c);
		dayField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 2;
		middlePanel.add(dayField, c);
		dayField.setEditable(false);

		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		middlePanel.add(timeLabel, c);
		timeField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 3;
		middlePanel.add(timeField, c);
		timeField.setEditable(false);

		JLabel costLabel = new JLabel("Cost:");
		costLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		middlePanel.add(costLabel, c);
		costField = new JTextField(10);
		costField.setEditable(false);

		c.gridx = 1;
		c.gridy = 4;
		middlePanel.add(costField, c);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 5;
		middlePanel.add(nameLabel, c);
		JTextField nameField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 5;
		middlePanel.add(nameField, c);

		JLabel citizenLabel = new JLabel("Citizenship:");
		citizenLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 6;
		middlePanel.add(citizenLabel, c);
		JTextField citizenField = new JTextField(10);
		c.gridx = 1;
		c.gridy = 6;
		middlePanel.add(citizenField, c);

		// bottom
		JButton reserveButton = new JButton("Reserve");
		bottomPanel.add(reserveButton);
		reserveButton.setPreferredSize(new Dimension(200, 30));
		reserveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().equals("")) { // if name field is null, print the following
					JOptionPane.showMessageDialog(null, "Error! Please enter required field to process");
				} else {
					try { // else called th makeReservation method, generateReseration method and print
							// the message box with the code
						rescode = reservationManager.generateReservationCode(flightsList.getSelectedValue());
						JOptionPane.showMessageDialog(null, "Reservation Created. Your code is" + " " + rescode);
						reservationManager.makeReservation(rescode, flightsList.getSelectedValue(), nameField.getText(),
								citizenField.getText());
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				}

			}
		});

		// add panels to main panel
		panel.add(upperPanel, BorderLayout.NORTH);
		panel.add(middlePanel, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		return panel;

	}

	private JPanel createSouthPanel() throws FileNotFoundException {

		FlightManager flight = new FlightManager();// create flightmanager object
		flight.getFlights();
		flight.getAirports();// access the getAirports methods, which returns the arraylist of the airports
		String[] array = flight.getAirports().toArray(new String[0]);// parse arraylist to array

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout());
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridBagLayout());
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());

		// upper
		JLabel titleLabel = new JLabel("Flight Finder");
		titleLabel.setFont(new Font("serif", Font.PLAIN, 29));
		upperPanel.add(titleLabel);

		// middle
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel fromLabel = new JLabel("From:");
		fromLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		middlePanel.add(fromLabel, c);
		JComboBox<String> fromField = new JComboBox<String>(array);

		c.gridx = 1;
		c.gridy = 0;
		middlePanel.add(fromField, c);
		fromField.setPreferredSize(new Dimension(500, 30));

		JLabel toLabel = new JLabel("To:");
		toLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		middlePanel.add(toLabel, c);
		JComboBox<String> toField = new JComboBox<String>(array);
		c.gridx = 1;
		c.gridy = 1;
		middlePanel.add(toField, c);
		toField.setPreferredSize(new Dimension(500, 30));

		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setHorizontalAlignment(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		middlePanel.add(dayLabel, c);
		JComboBox<String> dayField = new JComboBox<String>(days);
		c.gridx = 1;
		c.gridy = 2;
		middlePanel.add(dayField, c);
		dayField.setPreferredSize(new Dimension(500, 30));
		dayField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String daySelect = (String) dayField.getSelectedItem();
				dayField.addItem(daySelect);

			}
		});

		// bottom
		JButton findFlightsButton = new JButton("Find Flights");
		bottomPanel.add(findFlightsButton);
		findFlightsButton.setPreferredSize(new Dimension(550, 30));
		findFlightsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // Action Listener to display matched flight objects

				String inputDepart = (String) fromField.getSelectedItem();
				String inputArrival = (String) toField.getSelectedItem();
				String inputDay = (String) dayField.getSelectedItem();
				FlightManager flightManage;
				try {
					flightManage = new FlightManager();
					flightsModel.addAll(flightManage.findFlights(inputDepart, inputArrival, inputDay));// 
                // called the findfilght method, which returns flight and add to flight model
				} catch (FileNotFoundException e2) {

					e2.printStackTrace();
				}

			}
		});

		// add panels to main panel
		panel.add(upperPanel, BorderLayout.NORTH);
		panel.add(middlePanel, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {

		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		flightsModel = new DefaultListModel<>();

		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(flightsList);

		MyListSelectionListener mL = new MyListSelectionListener();

		flightsList.addListSelectionListener(mL);

		panel.add(scrollPane);

		return panel;
	}

	public class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Flight flight = flightsList.getSelectedValue();
			flightField.setText(flight.getCode());
			
			airlineField.setText("Sprawl Airway");
			
			dayField.setText(flight.getWeekday());
		
			timeField.setText(flight.getTime());
			
			costField.setText(flight.getCostPerSeat() + "");
			

		}
	}
}
