package kodlar;
import java.util.Calendar;;
public class Date {
	private int year,month,day;
	
	public Date(int day, int month,int year) throws ImpossibleInfo {
		if(day < 1 || day > 31)
			throw new ImpossibleInfo("Impossible day "+ day);
		if(month < 1 || month > 12)
			throw new ImpossibleInfo("Impossible month "+ month);
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public boolean isAfterThan(Date other) {
		if(year > other.getYear()) {
			return true;
		}
		else	if(year == other.getYear()) {
			if(month > other.getMonth()) {
				return true;
			}
			else if (month == other.getMonth()) {
				if(day > other.getDay()) {
					return true;
				}
				else {
					return false;
				}
			}
			return false ;
		}
		
			return false;
	}
	
	public boolean isBeforeThan(Date other) {
		if(!isAfterThan(other) && !isEqualsWith(other))
		return true;
		else return false;
	}
	public boolean isEqualsWith(Date other) {
		if(year == other.getYear() && day == other.getDay() && month == other.getMonth())
		return true;
		else return false;
	}
	
	public static Date getToday() throws ImpossibleInfo {
		Calendar cal = Calendar.getInstance();
		Date date = new Date(cal.DAY_OF_MONTH,cal.MONTH,cal.YEAR);
		return date;
	}
	
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
}
