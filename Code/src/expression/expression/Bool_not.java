package expression;

public class Bool_not  extends Expression {
	
	Expression right;
	
	public Bool_not(Expression right) {
		
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(not "  + right.toString() + ")";
	}
}
