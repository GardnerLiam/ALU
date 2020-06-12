package tools;

import numerics.FourBit;

public class StringToBase {
	public static String toBinary(String value, int s) {
		String v = Integer.toBinaryString(Integer.parseInt(value));
		while (v.length() < 4) {
			v = "0" + v;
		}
		
		if (s == 1) {
			return new FourBit(v).Complement().toString();
		}
		
		return v;
	}
	
	public static String fromBinary(String value) {
		int num = 0;
		if (value.charAt(0) == '1') {
			num = -8;
			int counter = 4;
			for (char c : value.substring(1).toCharArray()) {
				if (c == '1') {
					num += counter;
				}
				counter /= 2;
			}
		}else {
			int counter = 8;
			for (char c : value.toCharArray()) {
				if (c == '1') {
					num += counter;
				}
				counter /= 2;
			}
		}
		
		return Integer.toString(num);
	}
}
