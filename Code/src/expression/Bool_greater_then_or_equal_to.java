package expression;

public class Bool_greater_then_or_equal_to extends Expression {
	Expression left;
	Expression right;
	
	public Bool_greater_then_or_equal_to(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(>= " + left.toString() +" " + right.toString() + ")";
	}
}
