package expression;

public class consructor_value_assign_num extends Expression {
	String id;
	String parameter;
	String value;
	
	public consructor_value_assign_num(String id, String parameter, String value) {
		this.id = id;
		this.parameter = parameter;
		this.value = value;
	}
	
	@Override
	public String toString() {
		
		return "(assert(= (" + this.parameter + " " + this.id + " ) " + this.value + "))" ;
		//(assert(= (age r1) 15))
	}
}
