package expression;

public class parentheses_expr extends Expression {
	
	Expression middle;
	
	public parentheses_expr(Expression middle) {
	
		this.middle = middle;
	}
	
	@Override
	public String toString() {
		return middle.toString();
	}
}