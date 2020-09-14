package expression;

public class Arth_multi extends Expression {
	Expression left;
	Expression right;
	
	public Arth_multi(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(* " + left.toString() +" " + right.toString() + ")";
	}
}
