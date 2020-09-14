package expression;

import java.util.ArrayList;

public class constructor_declaration extends Expression {
	String id;
	 ArrayList<Expression> parameters;
	
	
	public constructor_declaration(String id, ArrayList<Expression> parameters) {
		this.id = id;
		this.parameters = parameters;
	
	}
	
	@Override
	public String toString() {
		
		return "(declare-datatypes () (( " +this.id + "(mk-R5 " +  ArrayListToString() +" ))))" ;
	}
	
	public String ArrayListToString() {
		String result = "";
	
		for (Expression e: parameters) {
			
			result +=  e.toString()  + " ";
		}
		return result;
	}
}
