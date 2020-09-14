package expression;

public class Bool_greater extends Expression {
	Expression left;
	Expression right;
	
	public Bool_greater(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(> " + left.toString() +" " + right.toString() + ")";
	}
}
