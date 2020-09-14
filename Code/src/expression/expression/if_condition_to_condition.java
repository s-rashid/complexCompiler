package expression;

public class if_condition_to_condition extends Expression {
	Expression if_expression;
	Expression true_expression;

	
	public if_condition_to_condition(Expression if_expression,Expression true_expression) {
		this.if_expression = if_expression;
		this.true_expression = true_expression;
	
	}
	
	@Override
	public String toString() {
		//return "(assert (ite " + this.if_expression.toString() + " "  + this.true_expression.toString() + " " + this.else_expression.toString() +  " ))";
		return "(assert (=> "+ this.if_expression + " " + this.true_expression + ")) ";
		//(assert (> r 0))
	}
}
