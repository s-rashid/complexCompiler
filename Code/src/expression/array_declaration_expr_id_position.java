package expression;

public class array_declaration_expr_id_position extends Expression {
	String id;
	String pos;
	
	public array_declaration_expr_id_position(String id, String pos) {
		this.id = id;
		this.pos = pos;
	}
	
	@Override
	public String toString() {
		return "(select "+this.id+ " "+ this.pos + " )";
	}
}