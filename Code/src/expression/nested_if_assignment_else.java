package expression;

import java.util.ArrayList;

public class nested_if_assignment_else extends Expression {
	Expression if_expression;
	 ArrayList<Expression> case_true;
	Expression else_expression;

	
	public nested_if_assignment_else(Expression if_expression, ArrayList<Expression> case_true, Expression else_expression) {
		this.if_expression = if_expression;
		this.case_true = case_true;
		this.else_expression = else_expression;
	
	}
	
	@Override
	public String toString() {
		//return "(assert (ite " + this.if_expression.toString() + " "  + this.true_expression.toString() + " " + this.else_expression.toString() +  " ))";
		//return "(assert (=> "+ this.if_expression + " " + this.true_expression + "))";
		//(assert (> r 0))
		return "";
	}
	
	public String ArrayListToString() {
		String result = "";
		if(case_true.size() == 1) {
			String e1 = case_true.get(0).toString();
			if(e1.toString().contains("assert")) {
			e1 = e1.replace("assert", "");
			e1 = e1.substring(1, e1.length()-1);
			}
			return e1;
		}
	
		
		int brack_counter = 0;
		for (Expression e: case_true) {
			String e1 = e.toString();
			if(e1.toString().contains("assert")) {
				e1 = e1.replace("assert", "");
				e1 = e1.substring(1, e1.length()-1);
			}
			
		
			brack_counter++;
			if(brack_counter < case_true.size()) {
			result += "( and " + e1 + " ";
			}
			else {
				result += e1;
				
			}
		}
		for(int i = 0; i< brack_counter -1 ; i++) {
			result = result + ")";
		}
		return result;
	}
}
