package kodlar;

public class ParkRecord {
	private Time enterTime;
	private Time exitTime;
	private Vehicle vehicle;
	
	public void setVehicle(Vehicle a) {
		vehicle = a;
	}
	
	public void setExitTime(Time exit) throws ImpossibleInfo {
		exitTime = new Time(exit.getHour(),exit.getMinute());
	}
	
	public void createAndAddVehicle(boolean b, String plate) {
		if(b)
			vehicle = new OfficialVehicle(plate);
		else
			vehicle = new RegularVehicle(plate);
	}
	
	public ParkRecord(Time enter) throws ImpossibleInfo {
		enterTime = new Time(enter.getHour(),enter.getMinute());
		
	}
	
	public String getPlate() {
		return vehicle.getPlate();
	}
	
	public boolean isSpecial() {
		return vehicle.isSpecial();
	}
	
	public int getParkingDuration() {
	
		return enterTime.getDifference(exitTime);
	}
	
}
