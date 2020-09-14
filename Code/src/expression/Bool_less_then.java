package expression;

public class Bool_less_then extends Expression {
	Expression left;
	Expression right;
	
	public Bool_less_then(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(< " + left.toString() +" " + right.toString() + ")";
	}
}
