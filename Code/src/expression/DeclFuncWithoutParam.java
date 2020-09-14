package expression;

import java.util.*;


public class DeclFuncWithoutParam extends Expression{
	String returnType;
	String id;
	List<Expression> statements;
	
	public DeclFuncWithoutParam(String returnType, String id, List<Expression> statements)
	{
		this.returnType = returnType;
		this.id = id;
		this.statements = statements;
	}
	
	
	
	public String StatementListToString() {
		String result = "";
		Iterator<Expression> it = statements.iterator();

		while (it.hasNext()) {
			Expression e = it.next();
			result += e.toString();
		}
		if (result.startsWith("(assert ")) {
			return result.substring(8, result.length() - 1);
		} else if (result.startsWith("(= "))
			return result.substring(5, result.length() - 1);

		return result;
	}

	@Override
	public String toString() {
		return "(define-fun " + id + "(" +  ") " +  returnType + "\n" + "\t" + StatementListToString() + ")";
	}


}