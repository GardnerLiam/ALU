package numerics;

public class ALU {

	
	FourBit firstNumber;
	FourBit secondNumber;
	int opCode;
	int status;
	
	private char[] operations;
	
	public ALU() {
		firstNumber = new FourBit("0000");
		secondNumber = new FourBit("0000");
		opCode = -1;
		status = 0;
		
		operations = new char[] {'+', '-', '|', '&', '^'};
	}
	
	public void loadFirstNumber(String num) {
		System.out.println("Load First Number:");
		System.out.println(num);
		if (num.charAt(0) == '-') {
			System.out.println("Negativity");
			this.firstNumber = new FourBit(tools.StringToBase.toBinary(num.substring(1), 1));
			System.out.println(this.firstNumber);
		}else {
			System.out.println("Positivity");
			this.firstNumber = new FourBit(tools.StringToBase.toBinary(num, 0));
			System.out.println(this.firstNumber);
		}
		System.out.println("END\n\n");
	}
	
	public void loadSecondNumber(String num) {
		if (num.charAt(0) == '-') {
			this.secondNumber = new FourBit(tools.StringToBase.toBinary(num.substring(1), 1));
		}else {
			this.secondNumber = new FourBit(tools.StringToBase.toBinary(num, 0));
		}
	}
	
	public void loadOpCode(char op) {
		for (int i = 0; i < operations.length; i++) {
			if (op == operations[i]) {
				this.opCode = i;
				return;
			}
		}
		this.opCode = -1;
	}
	
	public String evaluate() {
		status = 1;
		if (opCode == 0) {
			status = 0;
			return firstNumber.Add(secondNumber).toString();
		}else if (opCode == 1) {
			status = 0;
			return firstNumber.FinalSubtraction(secondNumber).toString();
		}else if (opCode == 2) {
			status = 0;
			return firstNumber.Or(secondNumber).toString();
		}else if (opCode == 3) {
			status = 0;
			return firstNumber.And(secondNumber).toString();
		}else if (opCode == 4) {
			status = 0;
			return firstNumber.Xor(secondNumber).toString();
		}
		status = 2;
		return "Syntax Error";
		
	}
	
	public void reset() {
		///firstNumber = new FourBit("0000");
		///secondNumber = new FourBit("0000");
		///opCode = -1;
		///status = 0;
	}

	public void printData() {
		System.out.println(firstNumber);
		System.out.println(secondNumber);
		System.out.println(opCode);
	}

	public FourBit getFirstNumber() {
		return firstNumber;
	}

	public FourBit getSecondNumber() {
		return secondNumber;
	}

	public int getOpCode() {
		return opCode;
	}

	public int getStatus() {
		return status;
	}

	public char[] getOperations() {
		return operations;
	}
	
	
}
