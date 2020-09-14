package expression;

public class Variable_Assignment  extends Expression {
	String id;
	String value;
	public Variable_Assignment(String id,String value) {
		this.id = id;
		this.value = value;
	}

	@Override
	public String toString() {
		return  "(assert (= "+ this.id + " "+ this.value +"))";
		//(assert (= r 5))
	}

	}

