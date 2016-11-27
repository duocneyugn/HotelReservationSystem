import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GuestModel {
	private HashMap<Guest, ArrayList<Room>> hotel;
	private ArrayList<Room> availableRooms;
	private ArrayList<ChangeListener> listeners;
	private int IDCounter;
	private int currentID;
	private Guest currentGuest;

	public GuestModel() {
		hotel = new HashMap<Guest, ArrayList<Room>>();
		listeners = new ArrayList<ChangeListener>();
		availableRooms = new ArrayList<Room>();
		IDCounter = 0;

		// Initialize the availableRooms arrayList
		for (int roomNum = 1; roomNum <= 20; roomNum++) {
			Room r = new Room();
			r.setRoomNumber(roomNum);
			r.setCurrentStatus(false);
			if (roomNum <= 10) {
				r.setRoomType("Economy");
			}
			else if (roomNum > 10) {
				r.setRoomType("Luxury");
			}
			availableRooms.add(r);
		}
	}

	/**
	 * Returns a formatted string of current available rooms of a room type
	 * @param type the room type requested
	 * @return s a string with data of current available rooms
	 */	
	public String getRooms(String type) {
		String s = "";
		for (Room r : availableRooms) {
			if (r.getRoomType().equals(type)) {
				s += r.getRoomNumber() + "\n";
			}
		}
		return s;
	}

	/**
	 * 
	 * @param ID of guest
	 * @throws Exception 
	 * @precondition: Guest ID is registered in the system
	 * @postcondition: Guest is logged in
	 */
	public void signIn(int ID) throws NullPointerException {
		for (Guest g : hotel.keySet()) {
			if (g.getUserID() == ID) {
				currentGuest = g;
				currentID = ID;
				break;
			}
		}
		if (currentGuest == null) {
			throw new NullPointerException();
		}
		System.out.println(currentGuest.getUserID());
	}

	/**
	 * @param username of the guest
	 * @precondition: Guest signing up has unique username and ID # 
	 * @precondition: Guest is added to the hotel
	 */
	public void signUp(String username) {
		Guest g = new Guest(IDCounter, username);
		IDCounter++;
		ArrayList<Room> roomList = new ArrayList<Room>();
		hotel.put(g, roomList); // Add guest to hotel
		System.out.println("Success! Your id is " + g.getUserID());
	}

	/**
	 * @param g the guest
	 * @param r the room to cancel
	 * @throws Exception 
	 * @precondition: Guest has the room reserved
	 * @postcondition: Room is removed from Guest's arraylist in hotel
	 */
	public void cancelReservation(Room r) throws Exception {
		if (!hotel.get(currentGuest).contains(r)) {
			throw new Exception(currentGuest.getUsername() + " did not reserve the room " + r.getRoomNumber());
		}
		hotel.get(currentGuest).remove(r); // Remove room from guest list of rooms
	}

	public void addRoom(int roomNumber) {
		Room r = availableRooms.get(roomNumber-1);
		r.setCurrentStatus(true);
		r.setStartDate(currentGuest.getStartDate());
		r.setEndDate(currentGuest.getEndDate());
		
		if (hotel.containsKey(currentGuest)) {
			hotel.get(currentGuest).add(r);
		}
		else {
			ArrayList<Room> guestRooms = new ArrayList<Room>();
			hotel.put(currentGuest, guestRooms);
		}
		
		availableRooms.remove(r);
		
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener c : listeners) {
			c.stateChanged(event);
		}
		System.out.println(r.toString());
	}

	/**
	 * Assigns a guest to a room until guest cancels the reservation
	 * @param checkIn the check-in date
	 * @param checkOut the check-out date
	 * @param roomType the 
	 * @precondition: check-in/check-out/room type is valid & user is signed in
	 * @postcondition: Room is removed from available rooms and added to the map of occupied rooms
	 */
	public void addRoom(String checkIn, String checkOut, String roomType) throws IllegalArgumentException, NullPointerException {
		//		if (currentGuest == null) {
		//			throw new NullPointerException();
		//		}
		if (checkIn.compareTo(checkOut) > 0) {
			throw new IllegalArgumentException();
		}
		Date checkInDate = new Date(checkIn);
		Date checkOutDate = new Date(checkOut);

		currentGuest.setStartDate(checkInDate);
		currentGuest.setEndDate(checkOutDate);

		Room room = null;

		if (roomType.equals("Economy")) {
			for (int roomNum = 0; roomNum < 10; roomNum++) {
				if (!availableRooms.get(roomNum).getCurrentStatus()) {
					room = availableRooms.get(roomNum);
					availableRooms.remove(roomNum);
					break;
				}
			}
		}
		else if (roomType.equals("Luxury")) {
			for (int roomNum = 10; roomNum < 20; roomNum++) {
				if (!availableRooms.get(roomNum).getCurrentStatus()) {
					room = availableRooms.get(roomNum);
					availableRooms.remove(roomNum);
					break;
				}
			}
		}

		room.setStartDate(checkInDate);
		room.setEndDate(checkOutDate);
		room.setCurrentStatus(true);

		if (hotel.containsKey(currentGuest)) {
			hotel.get(currentGuest).add(room);
		}
		else {
			ArrayList<Room> guestRooms = new ArrayList<Room>();
			hotel.put(currentGuest, guestRooms);
		}

		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener c : listeners) {
			c.stateChanged(event);
		}
	}

	/**
	 * Adds a ChangeListener to the listeners ArrayList
	 * @param listener to be added
	 * @postcondition: listener is added to the listeners ArrayList
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}

	public void updateGuestData(Date s, Date e) {
		currentGuest.setStartDate(s);
		currentGuest.setEndDate(e);
	}
}
