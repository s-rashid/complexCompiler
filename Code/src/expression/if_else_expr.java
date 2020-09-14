package expression;

public class if_else_expr extends Expression {
	Expression if_expression;
	Expression true_expression;
	Expression else_expression;
	
	public if_else_expr(Expression if_expression,Expression true_expression, Expression else_expression) {
		this.if_expression = if_expression;
		this.true_expression = true_expression;
		this.else_expression = else_expression;
	}
	
	@Override
	public String toString() {
		return "(assert (ite " + this.if_expression.toString() + " "  + this.true_expression.toString() + " " + this.else_expression.toString() +  " ))";
		//(assert (> r 0))
	}
}
