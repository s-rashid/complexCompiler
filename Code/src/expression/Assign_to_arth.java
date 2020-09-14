package expression;

public class Assign_to_arth extends Expression {
	String id;
	Expression right;
	
	public Assign_to_arth(String id,Expression right) {
		this.id = id;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return  " (assert (= "+ this.id +" " + this.right.toString() +"))";
	}
}
