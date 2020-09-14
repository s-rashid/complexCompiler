package expression;

import java.util.*;

public class DeclFuncWithParam extends Expression{
	String returnType;
	String funcName;
	List<String> paramId;
	List<String> paramVal;
	List<Expression> statements;
	
	public DeclFuncWithParam(String returnType, String funcName, List<String> paramId, List<String> paramVal, List<Expression> statements)
	{
		this.returnType = returnType;
		this.funcName = funcName;
		this.paramId = paramId;
		this.paramVal = paramVal;
		this.statements = statements;
	}
	
	
	
	public String ArrayListToString() {
		String result = "";
		Iterator<String> it1= paramId.iterator();
		Iterator<String> it2= paramVal.iterator();
		
		
		while(it1.hasNext() && it2.hasNext()) 
		{		
			result +=  "(" + it2.next()  + " " + it1.next() + ") ";
		}
		return result;
	}
	
	public String StatementListToString() {
		String result = "";
		Iterator<Expression> it = statements.iterator();
		
		
		while(it.hasNext()) 
		{		
			Expression e = it.next();
			if(e instanceof if_else_expr) {
				String temp = e.toString();
				result += temp.substring(8, temp.length() - 1);
			}
			else
				result +=  e.toString();
		}
		if(result.startsWith("(assert ")){
			return result.substring(8, result.length() -1); 
		}
		return result.substring(5, result.length() -1);
	}
	
	@Override
	public String toString() {
		return "(define-fun " + funcName + "(" + ArrayListToString() + ") " + returnType + "\n" + StatementListToString() + ")";
	}

}


