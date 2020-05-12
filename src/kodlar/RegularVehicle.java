package kodlar;

public class RegularVehicle implements Vehicle {

	String plate;
	
	@Override
	public String getPlate() {
		// TODO Auto-generated method stub
		return plate;
	}

	@Override
	public Subscription getSubscription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSpecial() {
		// TODO Auto-generated method stub
		return false;
	}
	public RegularVehicle(String plate) {
		this.plate = plate;
	}
}
