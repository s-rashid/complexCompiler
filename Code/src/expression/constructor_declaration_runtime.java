package expression;

public class constructor_declaration_runtime extends Expression {
	String id;
	String id2;
	
	public constructor_declaration_runtime(String id,  String id2) {
		this.id = id;
		this.id2 = id2;
		
	}
	
	@Override
	public String toString() {
		
		return "(declare-const " + this.id2 + " " + this.id + ")" ;
		//(declare-const r1 rec)
	}
}
