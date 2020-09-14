package expression;

public class array_declaration_expr extends Expression {
	String id;
	String pos;
	
	public array_declaration_expr(String id, String pos) {
		this.id = id;
		this.pos = pos;
	}
	
	@Override
	public String toString() {
		return "(select "+this.id+ " "+ this.pos + " )";
	}
}
