package kodlar;
import java.io.IOException;

@SuppressWarnings("serial")
public class ImpossibleInfo extends IOException{
	public ImpossibleInfo (String errorMessage) {
		super(errorMessage);
	}
}
