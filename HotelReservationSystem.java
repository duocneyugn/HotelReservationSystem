import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class HotelReservationSystem {
	public static void main(String[] args) {
		ArrayList<JComponent> a = new ArrayList<>();
		HotelModel hotel = new HotelModel(a);
		GuestModel gm = new GuestModel();
		gm.signUp("test");
		gm.signIn(0);
		ReservationPanel rp = new ReservationPanel(hotel, gm);
		a.add(new UserPreferencePanel(hotel, gm, rp));
		a.add(rp);
		
		HotelFrame frame = new HotelFrame(hotel);
		hotel.attach(frame);
	}
	
	
}