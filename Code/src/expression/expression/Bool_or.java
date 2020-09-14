package expression;

public class Bool_or  extends Expression {
	Expression left;
	Expression right;
	
	public Bool_or(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(or " + left.toString() +" " + right.toString() + ")";
	}
}
