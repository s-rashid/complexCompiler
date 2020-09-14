package expression;

public class if_condition_to_assignment extends Expression {
	Expression left;
	Expression right;
	
	public if_condition_to_assignment(Expression left,Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		//return "(assert " + left.toString() +")\n" + "(assert " + right.toString() +")";
		return "(assert ( => " + left.toString() + "  " + right.toString() +   "))";
	//	return "(assert (ite " + left.toString() + " " + right.toString() + " " + right.toString() + " ))";
		//(assert (> r 0))
	}
}
