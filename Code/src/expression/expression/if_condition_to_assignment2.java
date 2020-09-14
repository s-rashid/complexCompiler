package expression;

import java.util.ArrayList;

public class if_condition_to_assignment2 extends Expression {
	Expression condition;
	 ArrayList<Expression> case_true;
	
	public if_condition_to_assignment2(Expression condition, ArrayList<Expression> case_true) {
		this.condition = condition;
		this.case_true = case_true;
	}
	
	@Override
	public String toString() {
		return "(assert (=>" + condition.toString() +" (and " + ArrayListToString() + "))) ";
	}
	
	public String ArrayListToString() {
		String result = "";
		for (Expression e: case_true) {
			result += e.toString() + " ";
		}
		return result;
	}
}