package sambit.experiments.corner;

import java.util.Date;
import java.util.TimeZone;

public class Sambit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d = new Date(766354972 * 1000 + TimeZone.getDefault().getRawOffset());
		System.out.println(d.toString());
	}

}
