
public class Recursion {

	/*
	 * author: Arthur T. Manning - atmanning@dbq.edu
	 * 
	 * A classic recursion solution presented in two ways The easier problem is to
	 * convert from a given base to decimal, base 10 The second converts from base1
	 * to base2 given the string representation of base 1
	 * 
	 * This is provided as a 'utility' class just for doing calculations, no user
	 * interaction.
	 * 
	 * notes: there is not much error checking being done here. We'll leave that to
	 * the GUI software to filter out illegal values.
	 * 
	 * You can probably improve the efficiency or readability of this code!
	 * 
	 * What could you change to make it more readable or efficient?
	 * 
	 * What digits could you use to represent higher bases than 36?
	 * 
	 * Where could you put some error checking/prevention?
	 * 
	 */

	// this string holds the character digits for conversion from-to base 36
	static String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	// A basic problem: convert from any base < 36 to decimal
	// take base2 to the power of str.length() - 1,
	// add that number recursively to the value of the remaining digits
	//
	// usage: base2Dec( "111", 8) should return 73 decimal (64 + 8 + 1)
	public static Integer base2Dec(String str, int base) {

		if (str.length() <= 0)
			return 0;

		String c1 = str.substring(0, 1); // the leftmost digit

		// get the decimal value of the leftmost digit
		// The position (index) of this digit in the digits is its value
		int c1val = digits.indexOf(c1);

		// make sure this digit's value is less than base1 - ERROR
		if (base <= c1val) // error if digits not proper value for this base
			return 0;

		// convert this digit to decimal
		int decval = (int) (c1val * Math.pow((double) base, (double) str.length() - 1));

		// add this to the value of the rest of the digits
		return decval + base2Dec(str.substring(1), base);

	}

	// Recursively convert base 1 string to base 2 string - a bit more complicated
	// this involves a sort of nested, or conditional recursion
	// this recursive function behaves differently based on two parameters - str,
	// and val
	//
	//
	// The string str is recursively processed left to right, storing the decimal
	// result in val.
	// Once the string is zero-length, val is is then recursively converted to a
	// string
	// representing the base2 digits, going from right to left as the remainder is
	// calculated
	//
	public static String baseConvert(String str, int b1, int b2, int val) {

		// keep calculating val until str is empty
		// then convert val to base2
		if (str.length() <= 0) {

			// val now represents the decimal value to be converted to base 2
			// this is the part that recursively generates the base 2 string of digits

			if (0 == val) // end case - str is empty, val is 0
				return "";

			// pick the digit out of the character array representing the remainder
			int rb2 = val % b2;
			String cRight = digits.substring(rb2, rb2 + 1);

			// put this digit on the right side of the recursive more significant digits
			return baseConvert(str, b1, b2, val / b2) + cRight;

		} else { // recursively converting base1 to decimal

			// still processing the base1 string recursively
			// recursively converting base1 to decimal, continuing until str.length() is
			// zero

			String c1 = str.substring(0, 1);
			int cval = digits.indexOf(c1); //

			// raise base1 to the power of this digit's position in the string - 1, add it to val and then recurse
			return baseConvert(str.substring(1), b1, b2, (int) (val + cval * Math.pow(b1, str.length() - 1)));
		}

	}

}
