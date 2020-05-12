package kodlar;

public class SubscribedVehicle implements Vehicle {
	
	private Subscription sub;
	private String plate;
	
	public SubscribedVehicle(String plate) {
		this.plate = plate;
	}
	public void addSubscription(Subscription sub) {
		this.sub = sub;
	}
	@Override
	public String getPlate() {
		// TODO Auto-generated method stub
		return plate;
	}

	@Override
	public Subscription getSubscription() {
		// TODO Auto-generated method stub
		return sub;
	}

	@Override
	public boolean isSpecial() {
		// TODO Auto-generated method stub
		return false;
	}

}
