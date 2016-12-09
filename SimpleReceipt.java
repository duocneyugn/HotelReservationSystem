import java.util.ArrayList;
import java.util.Collections;

/**
 * The Comprehensive receipt that displays only reservations being made the
 * current transaction
 *
 * @author nhuluong
 *
 */
public class SimpleReceipt implements Receipt {
	private Reservations r;
	private Guest g;

	/**
	 * Construct a simple receipt object
	 * @param r2 the data model to retrieve data from
	 */
	public SimpleReceipt(Reservations r2) {
		r = r2;
		g = r.getCurrentGuest();
	}
	/**
	 * Display the user's ID
	 * @return the user's ID in String format
	 */
	public String showUserID() {
		return String.valueOf(g.getUserID());
	}
	/**
	 * Display the user's user name
	 * @return the user's user name in String format
	 */
	public String showUserName() {
		return g.getUsername();
	}

	/**
	 * Display the user's reservations being made in this particular transaction
	 * @return the user's reservations being made in this particular transaction
	 */
	public String showReservedRooms() {
		String allRooms = "";
		String currentDate = "";
		String previousDate = "";
		String currentType = "";
		String previousType = "";
		boolean first = true;
		int numType = 0;
		if (r.getData().containsKey(g)) {
			ArrayList<Room> current = r.getCurrentTransaction();
			Collections.sort(current, r.getComparator());
			for (Room r : current) {
				int sMonth = Integer.parseInt(String.valueOf(r.getStartDate().getMonth())) + 1;
				int eMonth = Integer.parseInt(String.valueOf(r.getStartDate().getMonth())) + 1;
				currentDate = String.valueOf(sMonth) + "/" + r.getStartDate().getDate() + "/"
						+ String.valueOf(r.getStartDate().getYear()).substring(1) + " - " + String.valueOf(eMonth) + "/"
						+ r.getEndDate().getDate() + "/" + String.valueOf(r.getStartDate().getYear()).substring(1);
				if (!currentDate.equals(previousDate)) {
					numType = 0;
					if (first) {
						allRooms += "Date:" + currentDate + "\n";
						first = false;
					} else {
						allRooms += "\n\nDate:" + currentDate + "\n";
						previousType = "";
					}
				}
				previousDate = currentDate;
				currentType = r.getRoomType();
				if (!currentType.equals(previousType)) {
					numType++;
					System.out.println(numType);
					if (numType == 1) {
						allRooms += "Room Type: " + r.getRoomType() + "\n";
					} else {
						allRooms += "\nRoom Type: " + r.getRoomType() + "\n";
					}
					allRooms += "Room Number: " + r.getRoomNumber();
				} else {
					allRooms += ", " + r.getRoomNumber();
				}
				previousType = currentType;
			}
		}
		return allRooms;

	}
	/**
	 * Display the amount due based on this current transaction
	 * @return the total amount due from this current transaction
	 */
	public String showAmountDue() {
		int totalPrice = 0;
		if (r.getData().containsKey(g)) {
			ArrayList<Room> current = r.getCurrentTransaction();
			for (Room r : current) {
				totalPrice += r.getPrice();

			}
		}
		return String.valueOf(totalPrice);
	}

}
