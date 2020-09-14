package expression;

public class nested_if_assignment_else extends Expression {
	Expression if_expression;
	Expression true_expression;
	Expression else_expression;

	
	public nested_if_assignment_else(Expression if_expression,Expression true_expression, Expression else_expression) {
		this.if_expression = if_expression;
		this.true_expression = true_expression;
		this.else_expression = else_expression;
	
	}
	
	@Override
	public String toString() {
		//return "(assert (ite " + this.if_expression.toString() + " "  + this.true_expression.toString() + " " + this.else_expression.toString() +  " ))";
		//return "(assert (=> "+ this.if_expression + " " + this.true_expression + "))";
		//(assert (> r 0))
		return "";
	}
	
	
}
