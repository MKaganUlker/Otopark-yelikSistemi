package kodlar;

public class OfficialVehicle implements Vehicle {

	private String plate;
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
		return true;
	}
	public OfficialVehicle(String plate) {
		this.plate = plate;
	}

}
