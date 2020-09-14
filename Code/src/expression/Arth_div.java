package expression;

public class Arth_div extends Expression {
	Expression left;
	Expression right;
	
	public Arth_div(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(div " + left.toString() +" " + right.toString() + ")";
	}
}
