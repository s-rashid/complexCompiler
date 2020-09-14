package expression;

public class Array_Declaration   extends Expression {
	String id;
	String type;
	
	public Array_Declaration(String id, String type) {
		this.id = id;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "(declare-const "+this.id+ " (Array Int"+ " "+ this.type + "))";
	}
}
