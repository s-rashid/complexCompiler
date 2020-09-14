package expression;

import java.util.Iterator;
import java.util.List;

public class FunctionInvocWithPrama extends Expression{
	String funcName;
	List<Expression> exprParam;
	
	public FunctionInvocWithPrama (String funcName, List<Expression> exprParam)
	{
		this.funcName = funcName;
		this.exprParam = exprParam;
	}
	
	public String ArrayListToString() {
		String result = "";
		Iterator<Expression> it = exprParam.iterator();
		
		
		while(it.hasNext()) 
		{		
			result +=  it.next().toString() + " ";
		}
		return result.substring(0, result.length() - 1);
	}
	
	@Override
	public String toString() {
		return "(" + funcName + " " + ArrayListToString() + ")";
	}

}


