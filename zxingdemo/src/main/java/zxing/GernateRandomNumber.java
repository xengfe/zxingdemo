package zxing;

import java.util.Random;

public class GernateRandomNumber {

	public static void main(String[] args) {
		Random rnd = new Random();
		for (int i = 0; i < 100; i++) {
			System.out.println(create(10000000, 99999999,rnd));
		}

	}

	public synchronized static long create(int min, int max,Random random) {
		if (min > max) {
			throw new IllegalArgumentException("min cannot exceed max.");
		} else {
			long range = max - (long) min + 1;
			long fraction = (long) (range * random.nextDouble());
			long randomNumber = fraction + (long) min;
			return randomNumber;
		}

	}

}
