package expression;

public class until_to_expression extends Expression {
	String id;
	Expression right;
	
	public until_to_expression(String id,Expression right) {
		this.id = id;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return  " (assert (= "+ this.id +" " + this.right.toString() +"))";
	}
}
