package expression;

public class Arth_mod extends Expression {
	Expression left;
	Expression right;
	
	public Arth_mod(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(MOD " + left.toString() +" " + right.toString() + ")";
	}
}

