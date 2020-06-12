package numerics;

public class FourBit {
	Bit bit0;
	Bit bit1;
	Bit bit2;
	Bit bit3;

	public FourBit(String v) {
		this.bit0 = new Bit(Integer.parseInt(v.substring(0, 1)));
		this.bit1 = new Bit(Integer.parseInt(v.substring(1, 2)));
		this.bit2 = new Bit(Integer.parseInt(v.substring(2, 3)));
		this.bit3 = new Bit(Integer.parseInt(v.substring(3, 4)));
	}

	public FourBit(Bit b0, Bit b1, Bit b2, Bit b3) {
		this.bit0 = b0;
		this.bit1 = b1;
		this.bit2 = b2;
		this.bit3 = b3;
	}

	public FourBit(Bit[] bits) {
		this.bit0 = bits[3];
		this.bit1 = bits[2];
		this.bit2 = bits[1];
		this.bit3 = bits[0];
	}

	public FourBit Not() {
		return new FourBit(this.bit0.not(), this.bit1.not(), this.bit2.not(), this.bit3.not());
	}

	public FourBit Add(FourBit o) {
		Bit[] bits = new Bit[4];

		Bit sum = bit3.Add(o.bit3)[0];
		Bit carry = bit3.Add(o.bit3)[1];

		bits[0] = sum.clone();
		
		bits[1] = bit2.FullAdd(o.bit2, carry)[0].clone();
		carry = bit2.FullAdd(o.bit2, carry)[1].clone();
		
		
		bits[2] = bit1.FullAdd(o.bit1, carry)[0].clone();
		carry = bit1.FullAdd(o.bit1, carry)[1].clone();
		
		bits[3] = bit0.FullAdd(o.bit0, carry)[0].clone();
		
		return new FourBit(bits);
	}
	
	public FourBit And(FourBit o) {
		return new FourBit(
				bit0.And(o.bit0),
				bit1.And(o.bit1),
				bit2.And(o.bit2),
				bit3.And(o.bit3)
				);
	}
	
	public FourBit Or(FourBit o) {
		return new FourBit(
				bit0.Or(o.bit0),
				bit1.Or(o.bit1),
				bit2.Or(o.bit2),
				bit3.Or(o.bit3)
				);
	}
	
	public FourBit Xor(FourBit o) {
		return new FourBit(
				bit0.Xor(o.bit0),
				bit1.Xor(o.bit1),
				bit2.Xor(o.bit2),
				bit3.Xor(o.bit3)
				);
	}
	
	public boolean Compare(FourBit o) {
		Bit c0 = bit0.And(o.bit0.not());
		Bit x0 = bit0.Xor(o.bit0).not();
		
		Bit c1 = x0.And(bit1.And(o.bit1.not()));
		Bit x1 = bit1.Xor(o.bit1).not();
		
		Bit c2 = x0.And(x1).And(bit2.And(o.bit2.not()));
		Bit x2 = bit2.Xor(o.bit2).not();
		
		Bit c3 = x0.And(x1).And(x2).And(bit3.And(o.bit3.not()));
		
		return c3.Or(c2).Or(c1).Or(c0).getValue() == 1;
	}
	
	public FourBit Subtract(FourBit o) {
		Bit[] bits = new Bit[4];

		Bit sum = bit3.Sub(o.bit3)[0];
		Bit carry = bit3.Sub(o.bit3)[1];

		bits[0] = sum.clone();
		
		bits[1] = bit2.FullSubtract(o.bit2, carry)[0].clone();
		carry = bit2.FullSubtract(o.bit2, carry)[1].clone();
		
		
		bits[2] = bit1.FullSubtract(o.bit1, carry)[0].clone();
		carry = bit1.FullSubtract(o.bit1, carry)[1].clone();
		
		bits[3] = bit0.FullSubtract(o.bit0, carry)[0].clone();
		
		return new FourBit(bits);
		
	}
	
	public FourBit FinalSubtraction(FourBit o) {
		if (this.Compare(o)) {
			return this.Subtract(o);
		}
		FourBit n = o.Subtract(this);
		return n.Complement();
	}

	public FourBit Complement() {
		FourBit temp = this.Not();
		
		return temp.Add(new FourBit("0001"));
	}

	@Override
	public String toString() {
		return this.bit0.toString() + this.bit1.toString() + this.bit2.toString() + this.bit3.toString();
	}
}
