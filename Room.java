import java.util.Date;

public class Room {
	private boolean isCurrent;
	private String roomType;
	private int roomNumber;
	private Date startDate;
	private Date endDate;
	
	public boolean getCurrentStatus(){
		return isCurrent;
	}
	
	public String getRoomType() {
		return roomType;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setCurrentStatus(boolean status){
		isCurrent = status;
	}
	
	public void setRoomType(String rType) {
		roomType = rType;
	}
	
	public void setRoomNumber(int rNumber) {
		roomNumber = rNumber;
	}
	
	public void setStartDate(Date sDate) {
		startDate = sDate;
	}
	
	public void setEndDate(Date eDate) {
		endDate = eDate;
	}
	
	public String toString() {
		String s = isCurrent + " " + roomType + " " + roomNumber + " " + startDate + " " + endDate;
		return s;
	}
}
