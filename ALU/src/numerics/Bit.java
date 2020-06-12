package numerics;

public class Bit {
	private int value;
	public Bit(int v) {
		value = v;
	}
	
	public Bit not () {
		return new Bit((value==0)?1:0);
	}
	
	public Bit And (Bit o) {
		return new Bit((value == 1 && o.value == 1)?1:0);
	}
	
	public Bit Or (Bit o) {
		return new Bit((value == 1 || o.value == 1)?1:0);
	}
	
	public Bit Xor (Bit o) {
		return new Bit (((value == 1 || o.value == 1)&&(value != o.value))?1:0);
	}
	
	public Bit[] Add (Bit o) {
		return new Bit[] {
				this.Xor(o),
				this.And(o)
		};
	}
	
	public Bit[] Complement(Bit o) {
		return o.Add(this.not());
	}
	
	public Bit[] Sub(Bit o) {
		return new Bit[] {
				this.Xor(o),
				o.And(this.not())
		};
	}
	
	public Bit[] FullAdd(Bit o, Bit c) {
		Bit sum = this.Add(o)[0].Add(c)[0];
		Bit carry = this.Add(o)[1].Or(this.Add(o)[0].Add(c)[1]);
		return new Bit[] {sum,carry};
	}
	
	public Bit[] FullSubtract(Bit o, Bit b) {
		Bit difference = this.Sub(o)[0].Sub(b)[0];
		Bit borrow = this.Sub(o)[1].Or(this.Sub(o)[0].Sub(b)[1]);
		
		return new Bit[] {difference, borrow};
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	@Override
	protected Bit clone() {
		return new Bit(this.value);
	}
}
