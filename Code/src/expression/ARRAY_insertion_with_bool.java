package expression;

public class ARRAY_insertion_with_bool    extends Expression {
	String id;
	String pos;
	String value;
	
	public ARRAY_insertion_with_bool(String id, String pos, String value) {
		this.id = id;
		this.pos = pos;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "(assert (= (store "+this.id+ " " + this.pos + " " +this.value + " ) " + this.id + "))" ;
	}
}
