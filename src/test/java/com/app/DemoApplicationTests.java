package com.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class DemoApplicationTests {

	public static void main(String[] args) {
		String input = "$$$";

		// Declare the number variable
		double number = 0;

		// Remove non-numeric characters except the decimal point
		try {
			number = Double.parseDouble(input.replaceAll("[^0-9.]", ""));
			System.out.println("Extracted number: " + number);
		} catch (NumberFormatException e) {
			System.out.println("You cannot convert the number, setting it to 0.");
		}

		System.out.println("Final number: " + number);



	}
	@Test
	void contextLoads() {
	}

}
