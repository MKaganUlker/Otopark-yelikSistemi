package kodlar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Vector;
import java.util.Scanner;
public class AutoPark {
	
	public SubscribedVehicle[] subscribedVehicles;
	private ParkRecord[] parkRecords;
	private Vector parkedVehicles;
	private double hourlyFee, incomeDaily;
	private int capacity,vehicleCount,i,j,size,subCount,parkCount;
	
	public AutoPark(double hourlyFee, int capacity) {
		this.hourlyFee = hourlyFee;
		this.capacity = capacity;
		parkedVehicles = new Vector(capacity);
		subscribedVehicles = new SubscribedVehicle[capacity];
		parkRecords = new ParkRecord[capacity];
		incomeDaily = 0.0;
		size = 0;
		subCount = 0;
		parkCount = 0;
	}
	
	public SubscribedVehicle searchVehicle(String plate) {
		i = 0;
		try {
		while((i < subCount) && (!subscribedVehicles[i].getPlate().equals(plate)))
		{

			System.out.println(subscribedVehicles[i].getPlate());
			i++;
		}
	
		if( (subscribedVehicles[i].getPlate().equals(plate))){
			return subscribedVehicles[i];
		}
		else { return subscribedVehicles[i];}
		}
		catch(NullPointerException asd) {
			return null;
		}
		

	}
	
	public double getDailyIncome() {
		return incomeDaily;
	}
	
	
	public boolean isParked(String plate) {
		
		if(parkedVehicles.contains(plate))
			return true;
		else
			return false;
	}
	
	
	private void enlargeVehicleArray() {
		parkedVehicles.setSize(capacity+1);
		capacity++;
	}
	
	
	
	public boolean addVehicle(SubscribedVehicle vehicle) {
		i = 0;
		while(i < subCount && vehicle != subscribedVehicles[i]) {
			i++;
		}
		if(i == subCount) {
			
			subscribedVehicles[subCount] = vehicle;
			subCount++;
			return true;
		}
		else
			return false;
	}
	
	
	
	public boolean vehicleEnters(String plate,Time enter, boolean isOfficial) throws ImpossibleInfo {
		i = 0;
		if(isParked(plate)) {
			return false;
		}
					
			else {
				
				if(subCount > 0) {
					
				
					while(i < subCount && !subscribedVehicles[i].getPlate().equals(plate)) {
						i++;
					}
					if(subscribedVehicles[i].getPlate().equals(plate)) {
						
						parkRecords[parkCount] = new ParkRecord(enter);
						parkRecords[parkCount].setVehicle(subscribedVehicles[i]);
						parkCount++;
					
						parkedVehicles.add(subscribedVehicles[i].getPlate());
						return true;
					}
				}
				
				parkRecords[parkCount] = new ParkRecord(enter);
				parkRecords[parkCount].createAndAddVehicle(isOfficial, plate);
				parkCount++;
				parkedVehicles.add(plate);
				return true;
				
			}	
		
		
	}
	
	public boolean vehicleExits(String plate, Time exit) throws ImpossibleInfo {
		i = 0;
		if(isParked(plate)) {
			
			while((i < parkCount) && (!plate.equals(parkRecords[i].getPlate()))) {
			
					i++;
			}
			parkRecords[i].setExitTime(exit);
			parkedVehicles.remove(plate);
			
			try{
				if(searchVehicle(plate).getSubscription().isValid()) {
					System.out.println("Vehicle is subscribed, not paying fees");
					return false;
				}
				else if(parkRecords[i].getParkingDuration()/60 > 0){
					System.out.println("Subscription is not valid, paying fees");
					incomeDaily = (parkRecords[i].getParkingDuration()/60) * hourlyFee;
					return true;
				}
			}
			catch(NullPointerException es) {
				
			}
			
			if(!parkRecords[i].isSpecial() && parkRecords[i].getParkingDuration()/60 > 0) {
				incomeDaily = (parkRecords[i].getParkingDuration()/60) * hourlyFee;
				System.out.println("Vehicle is regular, paying fees");
				return true;
			}
			System.out.println("Vehicle has been removed, not paying fees.");
			return false;
		}
		System.out.println("Vehicle is not in the autopark!");
		return false;	
	}
	
	
	public String toString() {
		return "This autopark has a capacity of "+capacity+" vehicles.\n" + "Current daily income is " + incomeDaily + " units."
				+"\n Hourly Fee is. "+hourlyFee+" units.\n" + " There are currently " +parkedVehicles.size()+" parked vehicles in the autopark"
				;
	}
	
	public static void main(String[] args) throws ImpossibleInfo {
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Scanner scanner3 = new Scanner(System.in);
		AutoPark autopark;
		String plateNo;
		double hourly;
		int day,month,year;
		int counter = 0;
		SubscribedVehicle tempVehicle;
		SubscribedVehicle subV[] = new SubscribedVehicle[10];
		Subscription sub[] = new Subscription[10];
		Date date[] = new Date[10];
		int choice = 1,choice2 = 1;
		Time time;
		boolean official;
		
		System.out.println("Enter hourlyfee of the autopark");
		hourly = scanner.nextDouble();
		System.out.println("Enter capacity of the autopark");
		day = scanner.nextInt();
		autopark = new AutoPark(hourly,day);
		
		while(choice != 0) {
			
			System.out.println("0: Exit");
			System.out.println("1: Autopark operations");
			System.out.println("2: Create Subscribed Vehicle");
			
			choice = scanner.nextInt();
			
			if(choice == 2) {
				
				System.out.println("Enter subscription end date");
				System.out.println("End day: ");
				day = scanner.nextInt();
				System.out.println("End month: ");
				month = scanner2.nextInt();
				System.out.println("End year: ");
				year = scanner3.nextInt();
				date[counter] = new Date(day,month,year);
				System.out.println("Enter subscription begin date");
				System.out.println("Begin day: ");
				day = scanner.nextInt();
				System.out.println("Begin month: ");
				month = scanner2.nextInt();
				System.out.println("Begin year: ");
				year = scanner3.nextInt();
				date[counter+1] = new Date(day,month,year);
				
				System.out.println("Enter plate no of vehicle");
				plateNo = scanner.nextLine();
				plateNo = scanner.nextLine();
				
				if(autopark.searchVehicle(plateNo) != null) {
					System.out.println("A vehicle with same plate number already exists!");
				}
				else {
					subV[counter] = new SubscribedVehicle(plateNo);
					sub[counter] = new Subscription(date[counter+1],date[counter],subV[counter].getPlate());
					System.out.println("Subscribed vehicle number:"+ counter+ " has been created");
					
					if(autopark.addVehicle(subV[counter]))
						System.out.println("And vehicle has been added to the array.");
					
					counter++;
				}
					
				
			}
			
			if(choice == 1) {
				
				System.out.println("0: Exit ");
				System.out.println("1: Search vehicle");
				System.out.println("2: Get daily income");
				System.out.println("3: Vehicle enters");
				System.out.println("4: Vehicle exits");
				System.out.println("5: Introduce self");
				System.out.println("6: Is parked?");
				
				choice2 = scanner.nextInt();
				
				if(choice2 == 1) {
					System.out.println("Enter plate number of vehicle");
					scanner.nextLine();
					plateNo = scanner.nextLine();
					
						tempVehicle = autopark.searchVehicle(plateNo);
						if(tempVehicle != null)
							System.out.println("Vehicle with plate number "+plateNo+" exists.");
				
						else
						System.out.println("Vehicle with plate number "+plateNo+" does not exist in our array");
					
					
				}
				
				if(choice2 == 2) {
					System.out.println("Daily income of our company is: "+autopark.getDailyIncome());
				}
				

				
	/*			if(choice2 == 3) {
					System.out.println("Enter number of the subscribed vehicle you want to add to the subscribed vehicles array");
					day = scanner.nextInt();
					if(autopark.addVehicle(subV[day]))
						System.out.println("Vehicle added successfully!");
					else
						System.out.println("Vehicle could not be added");
				}*/
				
				if(choice2 == 3) {
					System.out.println("Enter the enter time of vehicle");
					System.out.println("Enter hour of vehicle:");
					day = scanner.nextInt();
					System.out.println("Enter minute of vehicle");
					month = scanner.nextInt();
					try {
					time = new Time(day,month);
					System.out.println("Enter plate number of vehicle");
					scanner.nextLine();
					plateNo = scanner.nextLine();
					System.out.println(plateNo);
					System.out.println("Enter 1 if vehicle is official else 0");
					year = scanner.nextInt();
					if(year == 1)
						official = true;
					else 
						official = false;
					if(autopark.vehicleEnters(plateNo, time, official))
						System.out.println("Vehicle entered successfully!");
					else
						System.out.println("Vehicle could not enter!");
					}
					catch(ImpossibleInfo eas) {
						eas.printStackTrace();
					}

				}
				
				if(choice2 == 4) {
					System.out.println("Enter the exit time of vehicle");
					System.out.println("Exit hour of vehicle:");
					day = scanner.nextInt();
					System.out.println("Exit minute of vehicle");
					month = scanner.nextInt();
					System.out.println("Enter plate number of vehicle");
					scanner.nextLine();
					plateNo = scanner.nextLine();
					System.out.println(plateNo);
					try {
					time = new Time(day,month);
					autopark.vehicleExits(plateNo, time);
					}
					catch(ImpossibleInfo asd) {
						asd.printStackTrace();
					}
	
		
						
				}
				
				if(choice2 == 5) {
					System.out.println(autopark.toString());
				}
				
				if(choice2 == 6) {
					System.out.println("Enter plate number of the vehicle which you want to check its parking status");
					plateNo = scanner.nextLine();
					if(autopark.isParked(plateNo))
						System.out.println("Such vehicle is parked");
					else
						System.out.println("Such vehicle is not parked!");
				}
				
			}
			
			
		}
		System.out.println("Program has been closed succsessfully!");

	}
	
}
