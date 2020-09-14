package expression;

public class Variable_Declaration_no_type extends Expression {
	String id;
	String type;
	public Variable_Declaration_no_type(String id,String type) {
		this.id = id;
		this.type = type;
	}

	@Override
	public String toString() {
		return  "(declare-const "+ this.id + " "+ this.type +")";
		//(declare-const a Bool)
	}

	}
