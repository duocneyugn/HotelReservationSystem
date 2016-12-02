import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class HotelReservationSystem {
	public static void main(String[] args) {
		ArrayList<JComponent> a = new ArrayList<>();
		HotelModel hotel = new HotelModel(a);
		GuestModel gm = new GuestModel();
		
		a.add(new UserPanel(hotel, gm));
		HotelFrame frame = new HotelFrame(hotel);
		hotel.attach(frame);
	}
	
	
}