package expression;

public class Bool_and  extends Expression {
	Expression left;
	Expression right;
	
	public Bool_and(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(and " + left.toString() +" " + right.toString() + ")";
	}
}
