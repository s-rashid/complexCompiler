package expression;

public class ARRAY_insertion_with_num   extends Expression {
	String id;
	String pos;
	String value;
	
	public ARRAY_insertion_with_num(String id, String pos, String value) {
		this.id = id;
		this.pos = pos;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "(assert (= (store "+this.id+ " " + this.pos + " " +this.value + " ) " + this.id + "))" ;
	}
}
