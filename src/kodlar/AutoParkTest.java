package kodlar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AutoParkTest {

	@Test
	void test() throws ImpossibleInfo {
		Date date = new Date(13,05,2019);
		SubscribedVehicle vehicle = new SubscribedVehicle("123");
		Subscription sub = new Subscription(Date.getToday(),date,vehicle.getPlate());
		vehicle.addSubscription(sub);
		
		AutoPark otopark = new AutoPark(2.5,6);
	
		assertTrue(otopark.addVehicle(vehicle));
		assertEquals(otopark.searchVehicle("123"),vehicle);
		
		
		assertEquals(vehicle,otopark.searchVehicle("123"));
		assertEquals(vehicle,otopark.searchVehicle(vehicle.getPlate()));
		
		Time zaman = new Time(11,30);
		assertTrue(otopark.vehicleEnters("123",zaman , false));
		assertEquals(0.0,otopark.getDailyIncome());
		assertTrue(otopark.vehicleEnters("111", zaman, true));
		assertFalse(otopark.vehicleEnters("111", zaman, true));
		Time cikisZamani = new Time(15,50);
		assertFalse(otopark.vehicleExits("111", cikisZamani));
		
	}

}
