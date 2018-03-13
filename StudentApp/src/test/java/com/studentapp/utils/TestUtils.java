package com.studentapp.utils;

import java.util.Random;

public class TestUtils {

	public static String getRandomValue(){
		Random random = new Random();
		int radomInt = random.nextInt(100000);
		return Integer.toString(radomInt);
	}
}
