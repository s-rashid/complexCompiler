package expression;

public class FuncInvocWithoutParam extends Expression {

	String funcName;
	
	public FuncInvocWithoutParam (String funcName)
	{
		this.funcName = funcName;
	}
	
	@Override
	public String toString() {
		return "(" + funcName + "() )";
	}

}
