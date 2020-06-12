package tools;

public class TruthTableCompression {
	int bits[];
	
	public TruthTableCompression(int bit0, int bit1, int bit2, int bit3) {
		this.bits = new int[4];
		this.bits[0] = bit0;
		this.bits[1] = bit1;
		this.bits[2] = bit2;
		this.bits[3] = bit3;
	}
	
	
	public String convert() {
		String binvalue = "";
		for (int b : this.bits)
			binvalue += Integer.toString(b);
		return Integer.toString(Integer.parseInt(binvalue,2),16);
	}
}
