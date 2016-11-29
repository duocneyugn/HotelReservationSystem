import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPreferencePanel extends JPanel{
	private Date checkIn;
	private Date checkOut;
	private GuestModel model;
	private ReservationPanel rPanel;

	public UserPreferencePanel(HotelModel hm, GuestModel gm, ReservationPanel panel) {
		rPanel = panel;
		model = gm;
		setLayout(null);

		// Components in userPreferencePanel
		JLabel checkInLabel = new JLabel("Check in");
		checkInLabel.setBounds(200, 100, 50, 20);
		final JTextField checkInField = new JTextField();
		checkInField.setBounds(200, 120, 100, 50);
		checkInField.setFont(new Font("Sans-serif", Font.PLAIN, 14));
		checkInField.setHorizontalAlignment(JTextField.CENTER);


		JLabel checkOutLabel = new JLabel("Check out");
		checkOutLabel.setBounds(320,100,70,20);
		final JTextField checkOutField = new JTextField();
		checkOutField.setBounds(320,120,100,50);
		checkOutField.setFont(new Font("Sans-serif", Font.PLAIN, 14));
		checkOutField.setHorizontalAlignment(JTextField.CENTER);

		JLabel roomTypeLabel = new JLabel("Room type:");
		JButton luxuryRoomButton = new JButton("$200");
		JButton econRoomButton = new JButton("$80");

		roomTypeLabel.setBounds(200, 200, 80, 50);
		luxuryRoomButton.setBounds(280, 200, 80, 50);
		econRoomButton.setBounds(370, 200, 80, 50);

		luxuryRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Date checkIn = new Date(checkInField.getText());
					Date checkOut = new Date(checkOutField.getText());
					rPanel.setStartDate(checkIn);
					rPanel.setEndDate(checkOut);
					rPanel.setRoomType("Luxury");
					model.updateData(checkIn, checkOut);
					hm.update(0, rPanel);
				}
				catch(NullPointerException n) {
					System.out.println("Please log in");
				}
				catch(IllegalArgumentException e) {
					System.out.println("Please enter a valid check-in and check-out date");
				}
			}
		});

		econRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Date checkIn = new Date(checkInField.getText());
					Date checkOut = new Date(checkOutField.getText());
					rPanel.setStartDate(checkIn);
					rPanel.setEndDate(checkOut);
					rPanel.setRoomType("Economy");
					model.updateData(checkIn, checkOut);
					hm.update(0, rPanel);
				}
				catch(NullPointerException n) {
					System.out.println("Please log in");
				}
				catch(IllegalArgumentException e) {
					System.out.println("Please enter a valid check-in and check-out date");
				}
			}
		});
		add(checkInLabel);
		add(checkInField);
		add(checkOutLabel);
		add(checkOutField);
		add(roomTypeLabel);
		add(luxuryRoomButton);
		add(econRoomButton);
	}
}
