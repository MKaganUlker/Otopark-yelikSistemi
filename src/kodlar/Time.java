package kodlar;

public class Time {
	
	private int hour;
	private int minute;
	
	public Time(int hour,int minute) throws ImpossibleInfo {
		if(hour < 0 || hour > 24)
			throw new ImpossibleInfo("Impossible hour" + hour);
		if(minute < 0 || minute > 60)
			throw new ImpossibleInfo("Impossible minute" + minute);
		this.hour = hour;
		this.minute = minute;
	}
	public int getDifference(Time other) {
		return ((other.getHour() - hour) * 60 + other.getMinute() - minute );
	}
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
}
