package expression;

public class constructor_parameter extends Expression {
	String id;

	String value;
	
	public constructor_parameter(String id, String value) {
		this.id = id;
		this.value = value;
	}
	
	@Override
	public String toString() {
		
		return "( " + this.value + " " + this.id + ")" ;
	}
}
