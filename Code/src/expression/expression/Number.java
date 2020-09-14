package expression;

public class Number extends Expression {
	String num;
	
	public Number(String num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return this.num;
	}
}
