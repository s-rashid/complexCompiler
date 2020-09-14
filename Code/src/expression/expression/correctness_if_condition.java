package expression;

public class correctness_if_condition extends Expression {
	Expression precondition;
	Expression implmentation;
	Expression postcondition;
	
	public correctness_if_condition(Expression precondition,Expression implmentation, Expression postcondition) {
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
