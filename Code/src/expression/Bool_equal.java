package expression;

public class Bool_equal extends Expression {
	Expression left;
	Expression right;
	
	public Bool_equal(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(= " + left.toString() +" " + right.toString() + ")";
	}
}

