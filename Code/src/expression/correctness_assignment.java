package expression;

public class correctness_assignment  extends Expression {
	Expression precondition;
	Expression implmentation;
	Expression postcondition;
	
	public correctness_assignment(Expression precondition,Expression implmentation, Expression postcondition) {
		this.precondition = precondition;
		this.implmentation = implmentation;
		this.postcondition = postcondition;
	}
	
	@Override
	public String toString() {
		return "";
		//(assert (> r 0))
	}
}
