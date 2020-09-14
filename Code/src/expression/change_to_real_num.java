package expression;

public class change_to_real_num extends Expression {
	
	Expression right;
	
	public change_to_real_num(Expression right) {
		
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(to_real "+ right.toString() + ")";
	}
}