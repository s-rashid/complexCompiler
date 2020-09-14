package expression;

public class Variable_Declaration_with_value  extends Expression {
	String id;
	String type;
	String value;
	public Variable_Declaration_with_value(String id,String type,String value) {
		this.id = id;
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		return  "(declare-const "+ this.id + " "+ this.type +")\n (assert (= "+ this.id +" " + this.value +"))";
		
		//(declare-const a Bool)
		////(assert (= r 5))
	}

	}
