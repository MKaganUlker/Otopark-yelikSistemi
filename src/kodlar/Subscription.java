package kodlar;

public class Subscription {
	private SubscribedVehicle vehicle;
	private Date begin;
	private Date end;
	
	public Subscription(Date begin, Date end, String plate) throws ImpossibleInfo{
		if(end.isBeforeThan(begin))
			throw new ImpossibleInfo("Impossible info: end is before begin!");
		this.begin = begin;
		this.end = end;
		vehicle = new SubscribedVehicle(plate);
	}
	public boolean isValid() throws ImpossibleInfo {
		
		if(end.isAfterThan(Date.getToday()))
			return true;
		
		else 
			return false;
	}
}
