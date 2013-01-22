package kopfbisfuss;

import java.util.Calendar;

public class TestBox {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		Calendar c = Calendar.getInstance();
		c.set(1944, 10, 15, 8, 40);

		long tag1 = c.getTimeInMillis();

		tag1 += 1000 * 60 * 60;

		c.setTimeInMillis(tag1);
		System.out.println("neue Stunde: " + c.get(c.HOUR_OF_DAY));

		c.add(c.DATE, 35);
		System.out.println("35 Tage hinzurechnen: " + c.getTime());

		c.roll(c.DATE, 35);
		System.out.println("35 Tage rollieren: " + c.getTime());

		c.set(c.DATE, 1);
		System.out.println("auf 1 setzen: " + c.getTime());
	}

}
