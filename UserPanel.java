import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UserPanel extends JPanel{
	public UserPanel(HotelModel hotel, GuestModel guestData){
		setLayout(null);
		//Create two buttons
		JButton signup = new JButton("Sign Up");
		JButton signin = new JButton("Sign In");
		
		signup.setBounds(100, 50, 500, 150);
		signin.setBounds(100, 250, 500, 150);

		signup.setFont(new Font("Arial", Font.PLAIN, 20));
		signin.setFont(new Font("Arial", Font.PLAIN, 20));
		
		add(signup);
		add(signin);
		
		signup.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpPanel signup = new SignUpPanel(hotel, guestData);
				hotel.update(0, signup);
			}
			
		});
		
		signin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				SignInPanel signin = new SignInPanel(hotel, guestData);
				hotel.update(0, signin);
			}

		});
	}

}