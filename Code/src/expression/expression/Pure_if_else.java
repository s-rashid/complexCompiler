package expression;

import java.util.ArrayList;

public class Pure_if_else extends Expression {
	Expression condition;
	 ArrayList<Expression> case_true;
	 ArrayList<Expression> else_condition;
	
	public Pure_if_else(Expression condition, ArrayList<Expression> case_true, ArrayList<Expression> else_condition) {
		this.condition = condition;
		this.case_true = case_true;
		this.else_condition = else_condition;
	}
	
	@Override
	public String toString() {
		//return "(assert (=>" + condition.toString() +" (and " + ArrayListToString() + ")))";
		
		
		return "(assert (ite " + condition.toString() + "  " + ArrayListToString() + "  " + ArrayListToString_else() + " ))";
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
	
	
	
	public String ArrayListToString_else() {
		String result = "";
		
		if(else_condition.size() == 1) {
			String e1 = else_condition.get(0).toString();
			if(e1.toString().contains("assert")) {
			e1 = e1.replace("assert", "");
			e1 = e1.substring(1, e1.length()-1);
			}
			return e1 ;
		}
	
		
		int brack_counter = 0;
		for (Expression e: else_condition) {
			String e1 = e.toString();
			if(e1.toString().contains("assert")) {
				e1 = e1.replace("assert", "");
				e1 = e1.substring(1, e1.length()-1);
			}
			
		
			brack_counter++;
			if(brack_counter < else_condition.size()) {
			result += "( and " + e1 + " ";
			}
			else {
				result += e1;
				
			}
		}
		for(int i = 0; i< brack_counter -1 ; i++) {
			result = result + ")";
		}
		return result   ;
	}
	
	
}